package com.myrmicatech.globalguidebackend.service.location_log;

import com.myrmicatech.globalguidebackend.dto.LocationLogDto;
import com.myrmicatech.globalguidebackend.entity.LocationLog;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;

import java.util.UUID;

public interface LocationLogService extends GlobalGuideEntityService<UUID, LocationLog> {
}
