package com.myrmicatech.globalguidebackend.service.time;

import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.dto.TimeDto;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.entity.Time;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;

import java.util.UUID;

public interface TimeService extends GlobalGuideEntityService<UUID, Time> {

    Time save(TimeDto timeDto, Location location) throws Exception;
}
