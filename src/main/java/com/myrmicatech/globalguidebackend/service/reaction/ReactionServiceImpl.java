package com.myrmicatech.globalguidebackend.service.reaction;

import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.entity.ids.AccountReviewId;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.ReactionRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReactionServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Reaction> implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MapStructMapper mapStructMapper;

    public ReactionServiceImpl(ReactionRepository reactionRepository) {
        super(reactionRepository);
    }

    @Override
    public Reaction save(ReactionDto reactionDto) throws Exception {
        Account account = accountService.checkAccount();
        Review review = reviewService.findById(reactionDto.getReviewId());
        AccountReviewId accountReviewId = AccountReviewId.builder()
                .reviewId(review.getId())
                .userId(account.getId())
                .build();

       
        Optional<Reaction> optionalFoundReaction = reactionRepository.findByAccountReviewId(accountReviewId);
        if (optionalFoundReaction.isPresent()) {
            Reaction reaction = optionalFoundReaction.get();
            reaction.setReactionType(reactionDto.getReactionType());

            return reactionRepository.save(reaction);
        }

       
        Reaction reaction = mapStructMapper.reactionDtoToReaction(reactionDto);
        reaction.setAccount(account);
        reaction.setReview(review);
        reaction.setAccountReviewId(accountReviewId);

        return reactionRepository.save(reaction);
    }

    @Override
    public Reaction delete(UUID reviewId) throws Exception {
        Account account = accountService.checkAccount();
        AccountReviewId accountReviewId = AccountReviewId.builder()
                .reviewId(reviewId)
                .userId(account.getId())
                .build();

        Optional<Reaction> optionalReaction = reactionRepository.findByAccountReviewId(accountReviewId);
        if (optionalReaction.isEmpty()) {
            throw new Exception("Cannot find reaction!");
        }

        Reaction reaction = optionalReaction.get();
        reactionRepository.delete(reaction);

        return reaction;
    }
}
