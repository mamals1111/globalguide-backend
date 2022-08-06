package com.myrmicatech.globalguidebackend.service.time;

import com.myrmicatech.globalguidebackend.dto.TimeDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.entity.Time;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.ReactionRepository;
import com.myrmicatech.globalguidebackend.repository.TimeRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TimeServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Time> implements TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private AccountService accountService;
    @Autowired
    private MapStructMapper mapStructMapper;

    public TimeServiceImpl(TimeRepository timeRepository) {
        super(timeRepository);
    }

    @Override
    public Time save(TimeDto timeDto, Location location) throws Exception {
        Time time = mapStructMapper.timeDtoToTime(timeDto);
        Account account = accountService.checkAccount();
        time.setAccount(account);
        time.setLocation(location);

        return timeRepository.save(time);
    }
}
