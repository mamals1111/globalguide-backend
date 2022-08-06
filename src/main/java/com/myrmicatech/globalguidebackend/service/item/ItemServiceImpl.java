package com.myrmicatech.globalguidebackend.service.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myrmicatech.globalguidebackend.dto.EmailDto;
import com.myrmicatech.globalguidebackend.dto.ItemDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.entity.*;
import com.myrmicatech.globalguidebackend.enums.ItemFileType;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.payload.ItemWithOwnedResponse;
import com.myrmicatech.globalguidebackend.repository.ItemRepository;
import com.myrmicatech.globalguidebackend.repository.OwnedItemRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.file.FileStorageService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Item> implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private OwnedItemRepository ownedItemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        super(itemRepository);
    }

    @Override
    public Item save(ItemDto itemDto, MultipartFile iconFile, MultipartFile photoFile) throws Exception {
        Account account = accountService.checkAdmin();
        List<Item> items = itemRepository.findAllByName(itemDto.getName());
        if (!items.isEmpty()) {
            throw new Exception("Item has already been created!");
        }

        Item item = mapStructMapper.itemDtoToItem(itemDto);

       
        File icon = handleItemFile(item, account, iconFile, ItemFileType.ICON);
        File photo = handleItemFile(item, account, photoFile, ItemFileType.PHOTO);

        item.setIcon(icon);
        item.setPhoto(photo);

        Item itemSaved = itemRepository.save(item);

        return itemSaved;
    }

    @Override
    public List<ItemWithOwnedResponse> findItemsOwnedByAccount() throws Exception {
        Account account = accountService.checkAccount();
        List<OwnedItem> ownedItems = ownedItemRepository.findAllByAccount(account);
        List<Item> items = itemRepository.findAll();
        List<ItemWithOwnedResponse> itemWithOwnedResponses = items.stream().map(item -> {
            ItemWithOwnedResponse itemWithOwnedResponse = new ItemWithOwnedResponse();
            itemWithOwnedResponse.setName(item.getName());
            itemWithOwnedResponse.setIcon(item.getIcon());
            itemWithOwnedResponse.setPhoto(item.getPhoto());

            boolean isOwned = ownedItems.stream().anyMatch(ownedItem ->
                    item.getId().equals(ownedItem.getAccountItemId().getItemId()));

            itemWithOwnedResponse.setOwned(isOwned);

            return itemWithOwnedResponse;
        }).collect(Collectors.toList());

        return itemWithOwnedResponses;
    }

    @Override
    public Item findItemById(UUID itemId) throws Exception {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new Exception("Cannot find item!");
        }
        return optionalItem.get();
    }

    @Override
    public ItemDto convertStringToJson(String itemDtoString) throws Exception {
        ItemDto itemDto;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            itemDto = objectMapper.readValue(itemDtoString, ItemDto.class);
        } catch (Exception err) {
//            System.out.printf("Error ", err.toString());
            throw new Exception(err.getMessage());
        }

        return itemDto;
    }

    public File handleItemFile(Item currentItem, Account account, MultipartFile itemFile, ItemFileType itemFileType) {
        String fileName = fileStorageService.storeFile(itemFile);
        String avatarDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/downloadFile/")
                .path(fileName).toUriString();
       
        File itemFileToSave;

        if (itemFileType.equals(ItemFileType.ICON)) {
            itemFileToSave = File.builder()
                    .fileName(fileName)
                    .fileDownloadUri(avatarDownloadUri)
                    .fileType(itemFile.getContentType())
                    .size(itemFile.getSize())
                    .account(account)
                    .itemIcon(currentItem)
                    .build();
        } else {
            itemFileToSave = File.builder()
                    .fileName(fileName)
                    .fileDownloadUri(avatarDownloadUri)
                    .fileType(itemFile.getContentType())
                    .size(itemFile.getSize())
                    .account(account)
                    .itemPhoto(currentItem)
                    .build();
        }

        return itemFileToSave;
    }


}
