package com.myrmicatech.globalguidebackend.controller;

import com.myrmicatech.globalguidebackend.dto.CommentSaveDto;
import com.myrmicatech.globalguidebackend.dto.CommentUpdateDto;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/comments")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET, path = "findByReviewId/{reviewId}")
    public ResponseEntity<Object> findByLocationId(@PathVariable("reviewId") UUID reviewId) {
        try {
            List<Comment> comments = commentService.findAllByReviewId(reviewId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "private/save")
    public ResponseEntity<Object> save(@RequestBody CommentSaveDto commentSaveDto) {
        try {
            Comment comment = commentService.save(commentSaveDto);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "private/update/{commentId}")
    public ResponseEntity<Object> update(@RequestBody CommentUpdateDto commentUpdateDto, @PathVariable("commentId") UUID commentId) {
        try {
            Comment comment = commentService.update(commentId, commentUpdateDto);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
