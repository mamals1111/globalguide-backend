package com.myrmicatech.globalguidebackend.service.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myrmicatech.globalguidebackend.dto.LocationCoordinateDto;
import com.myrmicatech.globalguidebackend.dto.LocationDto;
import com.myrmicatech.globalguidebackend.dto.LocationNearestPlaceDto;
import com.myrmicatech.globalguidebackend.dto.ReviewDto;
import com.myrmicatech.globalguidebackend.entity.*;
import com.myrmicatech.globalguidebackend.entity.dto.ReviewCountAllAndAvgRatingDto;
import com.myrmicatech.globalguidebackend.entity.dto.ReviewCountGroupByRatingDto;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.repository.ReviewRepository;
import com.myrmicatech.globalguidebackend.service.account.AccountService;
import com.myrmicatech.globalguidebackend.service.file.FileStorageService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.service.item.ItemService;
import com.myrmicatech.globalguidebackend.service.location.LocationService;
import com.myrmicatech.globalguidebackend.service.owned_item.OwnedItemService;
import com.myrmicatech.globalguidebackend.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class ReviewServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Review> implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private OwnedItemService ownedItemService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Autowired
    private FileHandler fileHandler;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        super(reviewRepository);
    }

    @Override
    public List<Review> findAllByLocationId(UUID locationId) throws Exception {
        List<Review> reviews = reviewRepository.findAllByLocationId(locationId);
        return reviews;
    }

    @Override
    public Review findById(UUID reviewId) throws Exception {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            throw new Exception("Cannot find review!");
        }
        Review review = optionalReview.get();
        return review;
    }

    @Override
    public List<Review> findByNearestPlace(LocationNearestPlaceDto locationNearestPlaceDto) throws Exception {
//        List<Location> locations = locationService.findByNearestPlace(locationCoordinateDto);

        BigDecimal latitude = locationNearestPlaceDto.getLatitude();
        BigDecimal longitude = locationNearestPlaceDto.getLongitude();
        double radius = locationNearestPlaceDto.getRadius();
        if (radius <= 0) {
            radius = 30;
        }
//        List<Review> reviews = reviewRepository.findAllByNearestLocation(latitude, longitude);
       
//        List<Review> updatedReviews = reviews.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(Review::getLocationId))),
//                ArrayList::new));
//        HashSet<UUID> locationIdSet = new HashSet<>();
//        List<Review> filteredReviews = reviews.stream().filter(r -> locationIdSet.add(r.getLocation().getId())).collect(Collectors.toList());
//        return filteredReviews;


        List<Review> reviews = reviewRepository.findAllByNearestLocation(latitude, longitude, radius);

        return reviews;
    }

    @Override
    public List<Review> findReviewByAccountId(UUID accountId) throws Exception {
        Account account = accountService.findFirstById(accountId);
        List<Review> reviews = reviewRepository.findAllByAccount(account);

        return reviews;
    }

    @Override
    public Review save(ReviewDto reviewDto, List<MultipartFile> reviewFiles) throws Exception {
        Review review = mapStructMapper.reviewDtoToReview(reviewDto);
        Location location = locationService.findById(review.getLocationId());
        Item item = itemService.findItemById(reviewDto.getItemId());
        Account account = accountService.checkAccount();

       
        List<File> reviewFilesToSave = handleReviewFiles(review, account, reviewFiles);
        OwnedItem ownedItem = ownedItemService.findByAccountAndItem(item);

       
        int itemEnergy = ownedItem.getEnergy();
        if (itemEnergy <= 3) {
            itemEnergy = 15;
        } else {
            itemEnergy -= 3;
        }
        ownedItem.setEnergy(itemEnergy);
        ownedItemService.save(ownedItem);

       
        review.setReviewFiles(reviewFilesToSave);
        review.setLocation(location);
        review.setAccount(account);
        review.setOwnedItem(ownedItem);
        review.setPhotoVerified(true);
        review.setReviewVerified(true);
        review.setLocationVerified(true);

        Review savedReview = reviewRepository.saveAndFlush(review);

       
        ReviewCountAllAndAvgRatingDto reviewCountAllAndAvgRatingDto = reviewRepository.countAllAndCalculateAvgRatingByLocation(location);
        long totalReviewsAfterSaved = reviewCountAllAndAvgRatingDto.getTotalReviews();
        double avgRatingAfterSaved = reviewCountAllAndAvgRatingDto.getAvgRatings();

        location.setTotalReviews(totalReviewsAfterSaved);
        location.setAvgRating(avgRatingAfterSaved);
        locationService.save(location);

       
        account.setToken(account.getToken() + 15);
        accountService.save(account);

        return savedReview;
    }

    public ReviewDto convertStringToJson(String reviewDtoString) throws Exception {
        ReviewDto reviewDto = new ReviewDto();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            reviewDto = objectMapper.readValue(reviewDtoString, ReviewDto.class);
        } catch (Exception err) {
//            System.out.println("Error " + err);
            throw new Exception(err.getMessage());
        }

        return reviewDto;
    }

    @Override
    public List<ReviewCountGroupByRatingDto> countByLocationId(UUID locationId) throws Exception {
        Location location = locationService.findById(locationId);
        List<ReviewCountGroupByRatingDto> reviewCountGroupByRatingDtos = reviewRepository.countAllByLocationIdGroupByRating(location);
        return reviewCountGroupByRatingDtos;
    }

    @Override
    public List<Review> findAllByLocationCoordinate(LocationCoordinateDto locationCoordinateDto) throws Exception {
        Location location = locationService.findByCoordinate(locationCoordinateDto);
        List<Review> reviews = reviewRepository.findAllByLocationId(location.getId());
        return reviews;
    }

    @Override
    public Set<Review> findByKeyword(String keyword) throws Exception {
        Set<Review> reviews = reviewRepository.searchByKeyword(keyword);
        return reviews;
    }

    public List<File> handleReviewFiles(Review currentReview, Account account, List<MultipartFile> reviewFiles) {
        List<File> reviewFilesToSave = new ArrayList<>();
        for (MultipartFile file :
                reviewFiles) {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/downloadFile/")
                    .path(fileName).toUriString();
            File builtFile = File.builder()
                    .fileName(fileName)
                    .fileDownloadUri(fileDownloadUri)
                    .fileType(file.getContentType())
                    .size(file.getSize())
                    .account(account)
                    .review(currentReview)
                    .build();
            reviewFilesToSave.add(builtFile);
        }

        return reviewFilesToSave;
    }

}
