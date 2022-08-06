package com.myrmicatech.globalguidebackend.service.review;

import com.myrmicatech.globalguidebackend.dto.LocationCoordinateDto;
import com.myrmicatech.globalguidebackend.dto.LocationNearestPlaceDto;
import com.myrmicatech.globalguidebackend.dto.ReviewDto;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.entity.dto.ReviewCountGroupByRatingDto;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ReviewService extends GlobalGuideEntityService<UUID, Review> {
    List<Review> findAllByLocationId(UUID locationId) throws Exception;
    Review findById(UUID reviewId) throws Exception;
    List<Review> findByNearestPlace(LocationNearestPlaceDto locationNearestPlaceDto) throws Exception;
    List<Review> findReviewByAccountId(UUID accountId) throws Exception;
    Review save(ReviewDto reviewDto, List<MultipartFile> reviewFiles) throws Exception;
    ReviewDto convertStringToJson(String reviewDtoString) throws Exception;
    List<ReviewCountGroupByRatingDto> countByLocationId(UUID locationId) throws Exception;
    List<Review> findAllByLocationCoordinate(LocationCoordinateDto locationCoordinateDto) throws Exception;

    Set<Review> findByKeyword(String keyword) throws Exception;
}
