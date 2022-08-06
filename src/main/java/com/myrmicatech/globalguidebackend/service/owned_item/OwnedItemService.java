package com.myrmicatech.globalguidebackend.service.owned_item;

import com.myrmicatech.globalguidebackend.entity.Item;
import com.myrmicatech.globalguidebackend.entity.OwnedItem;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;

import java.util.List;
import java.util.UUID;

public interface OwnedItemService extends GlobalGuideEntityService<UUID, OwnedItem> {

    OwnedItem save(UUID itemId) throws Exception;

    List<OwnedItem> findAllByAccount() throws Exception;

    OwnedItem findByAccountAndItem(Item item) throws Exception;
}
