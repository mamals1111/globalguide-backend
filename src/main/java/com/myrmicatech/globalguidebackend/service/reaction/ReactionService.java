package com.myrmicatech.globalguidebackend.service.reaction;

import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.entity.Otp;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;

import java.util.UUID;

public interface ReactionService extends GlobalGuideEntityService<UUID, Reaction> {
    Reaction save(ReactionDto reactionDto) throws Exception;

    Reaction delete(UUID reviewId) throws Exception;
}
