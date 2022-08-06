package com.myrmicatech.globalguidebackend.controller;

import com.myrmicatech.globalguidebackend.dto.LocationCoordinateDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.dto.LocationNearestPlaceDto;
import com.myrmicatech.globalguidebackend.dto.ReviewDto;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.entity.dto.ReviewCountGroupByRatingDto;
import com.myrmicatech.globalguidebackend.service.location.LocationService;
import com.myrmicatech.globalguidebackend.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.GET, path = "findByLocationId/{locationId}")
    public ResponseEntity<Object> findByLocationId(@PathVariable("locationId") UUID locationId) {
        try {
            List<Review> reviews = reviewService.findAllByLocationId(locationId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "private/save")
    public ResponseEntity<Object> save(@RequestPart("review") String reviewDtoString,
                                       @RequestPart("reviewFiles") List<MultipartFile> reviewFiles) {
        try {
            ReviewDto reviewDto = reviewService.convertStringToJson(reviewDtoString);
            Review review = reviewService.save(reviewDto, reviewFiles);
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "countByLocation/{locationId}")
    public ResponseEntity<Object> countByLocation(@PathVariable("locationId") UUID locationId) {
        try {
            List<ReviewCountGroupByRatingDto> reviewCountGroupByRatingDtos = reviewService.countByLocationId(locationId);
            return new ResponseEntity<>(reviewCountGroupByRatingDtos, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("Cannot find account!")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "findByNearestPlace")
    public ResponseEntity<Object> findByNearestPlace(@RequestBody LocationNearestPlaceDto locationNearestPlaceDto) {
        try {
            List<Review> reviews = reviewService.findByNearestPlace(locationNearestPlaceDto);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findByAccount/{accountId}")
    public ResponseEntity<Object> findByAccount(@PathVariable UUID accountId) {
        try {
            List<Review> reviews = reviewService.findReviewByAccountId(accountId);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findByKeyword")
    public ResponseEntity<Object> findByKeyword(@RequestParam("keyword") String keyword) {
        try {
            Set<Review> locations = reviewService.findByKeyword(keyword);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    @RequestMapping(method = RequestMethod.POST, path = "findAllByLocationCoordinate")
//    public ResponseEntity<Object> findAllByLocationCoordinate(@RequestBody LocationCoordinateDto locationCoordinateDto) {
//        try {
//            List<Review> reviews = reviewService.findAllByLocationCoordinate(locationCoordinateDto);
//            return new ResponseEntity<>(reviews, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
