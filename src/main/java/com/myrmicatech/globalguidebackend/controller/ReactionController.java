package com.myrmicatech.globalguidebackend.controller;

import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.dto.ReviewDto;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.service.reaction.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/reactions")
@CrossOrigin
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @RequestMapping(method = RequestMethod.POST, path = "private/save")
    public ResponseEntity<Object> save(@RequestBody ReactionDto reactionDto) {
        try {
            Reaction reaction = reactionService.save(reactionDto);
            return new ResponseEntity<>(reaction, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "private/delete/{reviewId}")
    public ResponseEntity<Object> save(@PathVariable("reviewId") UUID reviewId) {
        try {
            Reaction reaction = reactionService.delete(reviewId);
            return new ResponseEntity<>(reaction, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
