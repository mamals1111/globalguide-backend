package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Item;
import com.myrmicatech.globalguidebackend.entity.OwnedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OwnedItemRepository extends JpaRepository<OwnedItem, UUID>, JpaSpecificationExecutor<OwnedItem> {

    List<OwnedItem> findAllByAccount(Account account);

    Optional<OwnedItem> findByAccountAndItem(Account account, Item item);

    List<OwnedItem> findAllByItem(Item item);
}
