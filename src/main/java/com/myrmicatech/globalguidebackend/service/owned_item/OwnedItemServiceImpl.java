package com.myrmicatech.globalguidebackend.service.owned_item;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Item;
import com.myrmicatech.globalguidebackend.entity.OwnedItem;
import com.myrmicatech.globalguidebackend.entity.ids.AccountItemId;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.ItemRepository;
import com.myrmicatech.globalguidebackend.repository.OwnedItemRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.file.FileStorageService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnedItemServiceImpl extends GlobalGuideEntityServiceImpl<UUID, OwnedItem> implements OwnedItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OwnedItemRepository ownedItemRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MapStructMapper mapStructMapper;

    public OwnedItemServiceImpl(OwnedItemRepository ownedItemRepository) {
        super(ownedItemRepository);
    }

    @Override
    public OwnedItem save(UUID itemId) throws Exception {
        Account account = accountService.checkAccount();
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new Exception("Cannot find item!");
        }

        Item item = optionalItem.get();

        List<OwnedItem> ownedItems = ownedItemRepository.findAllByItem(item);
        if (!ownedItems.isEmpty()) {
            throw new Exception("Item is already owned!");
        }

        AccountItemId accountItemId = AccountItemId.builder()
                .itemId(item.getId())
                .userId(account.getId())
                .build();

        OwnedItem ownedItem = OwnedItem.builder()
                .hashId(UUID.randomUUID())
                .level(item.getDefaultLevel())
                .energy(item.getDefaultEnergy())
                .accountItemId(accountItemId)
                .item(item)
                .account(account)
                .build();

        return ownedItemRepository.save(ownedItem);
    }

    @Override
    public List<OwnedItem> findAllByAccount() throws Exception {
        Account account = accountService.checkAccount();
        List<OwnedItem> ownedItems = ownedItemRepository.findAllByAccount(account);
        return ownedItems;
    }

    @Override
    public OwnedItem findByAccountAndItem(Item item) throws Exception {
        Account account = accountService.checkAccount();
        Optional<OwnedItem> optionalOwnedItem = ownedItemRepository.findByAccountAndItem(account, item);
        if (optionalOwnedItem.isEmpty()) {
            throw new Exception("Cannot find owned item!");
        }
        return optionalOwnedItem.get();
    }
}
