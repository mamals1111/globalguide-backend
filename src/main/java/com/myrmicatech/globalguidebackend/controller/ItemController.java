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
@RequestMapping(path = "api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OwnedItemService ownedItemService;

    @RequestMapping(method = RequestMethod.POST, path = "admin/save")
    public ResponseEntity<Object> save(@RequestPart("item") String itemDtoString,
                                       @RequestPart("iconFile") MultipartFile iconFile,
                                       @RequestPart("photoFile") MultipartFile photoFile) {
        try {
            ItemDto itemDto = itemService.convertStringToJson(itemDtoString);
            Item item = itemService.save(itemDto, iconFile, photoFile);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")
                    || e.getMessage().contains("Account is not ADMIN!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "private/findItemsOwnedByAccount")
    public ResponseEntity<Object> findItemsOwnedByAccount() {
        try {
            List<ItemWithOwnedResponse> itemWithOwnedResponseList = itemService.findItemsOwnedByAccount();
            return new ResponseEntity<>(itemWithOwnedResponseList, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
