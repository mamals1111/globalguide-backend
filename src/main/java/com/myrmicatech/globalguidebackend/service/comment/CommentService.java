package com.myrmicatech.globalguidebackend.service.comment;

import com.myrmicatech.globalguidebackend.dto.CommentSaveDto;
import com.myrmicatech.globalguidebackend.dto.CommentUpdateDto;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;

import java.util.List;
import java.util.UUID;

public interface CommentService extends GlobalGuideEntityService<UUID, Comment> {

    Comment save(CommentSaveDto commentSaveDto) throws Exception;

    Comment update(UUID commentId, CommentUpdateDto commentUpdateDto) throws Exception;

    List<Comment> findAllByReviewId(UUID reviewId) throws Exception;
}
