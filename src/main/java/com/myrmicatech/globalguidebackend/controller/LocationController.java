package com.myrmicatech.globalguidebackend.controller;

import com.myrmicatech.globalguidebackend.dto.LocationAreaDto;
import com.myrmicatech.globalguidebackend.dto.LocationCoordinateDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.dto.LocationRadiusDto;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.service.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/locations")
@CrossOrigin
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(method = RequestMethod.POST, path = "private/save")
    public ResponseEntity<Object> save(@RequestPart("location") String locationDtoString,
                                       @RequestPart("bannerFiles") List<MultipartFile> bannerFiles,
                                       @RequestPart("avatarFile") MultipartFile avatarFile) {
        try {
            LocationDto locationDto = locationService.convertStringToJson(locationDtoString);
            Location location = locationService.save(locationDto, bannerFiles, avatarFile);
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "private/update/{locationId}")
    public ResponseEntity<Object> update(@PathVariable("locationId") UUID locationId,
                                         @RequestPart("location") String locationDtoString,
                                         @RequestPart("bannerFiles") List<MultipartFile> bannerFiles,
                                         @RequestPart("avatarFile") MultipartFile avatarFile) {
        try {
            LocationDto locationDto = locationService.convertStringToJson(locationDtoString);
            Location location = locationService.update(locationId, locationDto, bannerFiles, avatarFile);
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "findByArea")
    public ResponseEntity<Object> findByArea(@RequestBody LocationAreaDto locationAreaDto) {
        try {
            List<Location> locations = locationService.findByArea(locationAreaDto);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findById/{locationId}")
    public ResponseEntity<Object> findById(@PathVariable("locationId") UUID locationId) {
        try {
            Location location = locationService.findById(locationId);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "findByCoordinate")
    public ResponseEntity<Object> findByCoordinate(@RequestBody LocationCoordinateDto locationCoordinateDto) {
        try {
            Location location = locationService.findByCoordinate(locationCoordinateDto);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findByKeyword")
    public ResponseEntity<Object> findByKeyword(@RequestParam("keyword") String keyword) {
        try {
            Set<Location> locations = locationService.findByKeyword(keyword);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "findByRadius")
    public ResponseEntity<Object> findByRadius(@RequestBody LocationRadiusDto locationRadiusDto) {
        try {
            List<Location> locations = locationService.findByRadius(locationRadiusDto);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
