package com.myrmicatech.globalguidebackend.service.item;

import com.myrmicatech.globalguidebackend.dto.ItemDto;
import com.myrmicatech.globalguidebackend.entity.Item;
import com.myrmicatech.globalguidebackend.payload.ItemWithOwnedResponse;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemService extends GlobalGuideEntityService<UUID, Item> {

    Item save(ItemDto itemDto, MultipartFile itemFile, MultipartFile photoFile) throws Exception;

    List<ItemWithOwnedResponse> findItemsOwnedByAccount() throws Exception;

    Item findItemById(UUID itemId) throws Exception;

    ItemDto convertStringToJson(String itemDtoString) throws Exception;

}
