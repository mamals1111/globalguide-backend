package com.myrmicatech.globalguidebackend.service.location;

import com.myrmicatech.globalguidebackend.dto.LocationAreaDto;
import com.myrmicatech.globalguidebackend.dto.LocationCoordinateDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.dto.LocationRadiusDto;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface LocationService extends GlobalGuideEntityService<UUID, Location> {
    List<Location> findLocation();

    Location save(LocationDto locationDto, List<MultipartFile> bannerFiles, MultipartFile avatarFile) throws Exception;

    Location update(UUID locationId, LocationDto locationDto, List<MultipartFile> bannerFiles, MultipartFile avatarFile) throws Exception;

    List<Location> findByArea(LocationAreaDto locationAreaDto) throws Exception;

    Location findByCoordinate(LocationCoordinateDto locationCoordinateDto) throws Exception;

    List<Location> findByNearestPlace(LocationCoordinateDto locationCoordinateDto) throws Exception;

    LocationDto convertStringToJson(String locationDtoString) throws Exception;

    Set<Location> findByKeyword(String keyword) throws Exception;

    Location findById(UUID locationId) throws Exception;

    List<Location> findByRadius(LocationRadiusDto locationRadiusDto) throws Exception;
}
