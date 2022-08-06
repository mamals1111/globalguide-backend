package com.myrmicatech.globalguidebackend.service.location_log;

import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.LocationLog;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.entity.ids.AccountReviewId;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.LocationLogRepository;
import com.myrmicatech.globalguidebackend.repository.ReactionRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationLogServiceImpl extends GlobalGuideEntityServiceImpl<UUID, LocationLog> implements LocationLogService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MapStructMapper mapStructMapper;

    public LocationLogServiceImpl(LocationLogRepository locationLogRepository) {
        super(locationLogRepository);
    }

}
