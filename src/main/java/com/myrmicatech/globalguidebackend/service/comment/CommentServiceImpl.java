package com.myrmicatech.globalguidebackend.service.comment;

import com.myrmicatech.globalguidebackend.dto.CommentSaveDto;
import com.myrmicatech.globalguidebackend.dto.CommentUpdateDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.CommentRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Comment> implements CommentService {

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        super(commentRepository);
    }

    @Override
    public Comment save(CommentSaveDto commentSaveDto) throws Exception {
        Comment comment = mapStructMapper.commentSaveDtoToComment(commentSaveDto);
        Account account = accountService.checkAccount();
        Review review = reviewService.findById(commentSaveDto.getReviewId());

        comment.setAccount(account);
        comment.setReview(review);

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(UUID commentId, CommentUpdateDto commentUpdateDto) throws Exception {
        Account account = accountService.checkAccount();
        Optional<Comment> optionalComment = commentRepository.findFirstByIdAndAccount(commentId, account);
        if (optionalComment.isEmpty()) {
            throw new Exception("Cannot find comment");
        }

        Comment comment = optionalComment.get();
        mapStructMapper.update(comment, commentUpdateDto);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllByReviewId(UUID reviewId) throws Exception {
        List<Comment> comments = commentRepository.findAllByReviewId(reviewId);
        return comments;
    }
}
