package com.myrmicatech.globalguidebackend.controller;

import com.myrmicatech.globalguidebackend.dto.ItemDto;
import com.myrmicatech.globalguidebackend.entity.Item;
import com.myrmicatech.globalguidebackend.entity.OwnedItem;
import com.myrmicatech.globalguidebackend.payload.ItemWithOwnedResponse;
import com.myrmicatech.globalguidebackend.service.item.ItemService;
import com.myrmicatech.globalguidebackend.service.owned_item.OwnedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/ownedItems")
public class OwnedItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OwnedItemService ownedItemService;


    @RequestMapping(method = RequestMethod.POST, path = "private/save/{itemId}")
    public ResponseEntity<Object> saveOwnedItem(@PathVariable("itemId") UUID itemId) {
        try {
            OwnedItem ownedItem = ownedItemService.save(itemId);
            return new ResponseEntity<>(ownedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "private/findAllByAccount")
    public ResponseEntity<Object> findAllByAccount() {
        try {
            List<OwnedItem> ownedItem = ownedItemService.findAllByAccount();
            return new ResponseEntity<>(ownedItem, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
