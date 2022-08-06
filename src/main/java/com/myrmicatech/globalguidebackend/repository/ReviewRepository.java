package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.entity.Review;
import com.myrmicatech.globalguidebackend.entity.dto.ReviewCountAllAndAvgRatingDto;
import com.myrmicatech.globalguidebackend.entity.dto.ReviewCountGroupByRatingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID>, JpaSpecificationExecutor<Review> {

    List<Review> findAllByLocationId(UUID locationId);

    List<Review> findAllByAccount(Account account);

    @Query("select new com.myrmicatech.globalguidebackend.entity.dto.ReviewCountAllAndAvgRatingDto(count(r), avg(r.rating)) from Review r join r.location " +
            "where r.location = :#{#location} and r.isDeleted = false")
    ReviewCountAllAndAvgRatingDto countAllAndCalculateAvgRatingByLocation(Location location);

    @Query("select new com.myrmicatech.globalguidebackend.entity.dto.ReviewCountGroupByRatingDto(r.rating, count(r.rating)) from Review r " +
            "where r.isDeleted = false " +
            "and r.location = ?1 " +
            "group by r.rating")
    List<ReviewCountGroupByRatingDto> countAllByLocationIdGroupByRating(Location location);

    List<Review> findAllByLocationInOrderByCreatedDateDesc(List<Location> locations);

//    @Query("select r from Review r join r.location l where r.isDeleted = false " +
//            "order by sqrt((l.latitude - ?1) * (l.latitude - ?1) + (l.longitude - ?2) * (l.longitude - ?2)) asc, r.createdDate desc")
//    List<Review> findAllByNearestLocation(BigDecimal latitude, BigDecimal longitude);

    @Query("select r from Review r join r.location l where r.isDeleted = false " +
            "and sqrt((l.latitude - ?1) * (l.latitude - ?1) + (l.longitude - ?2) * (l.longitude - ?2)) < ?3 " +
            "order by r.createdDate desc")
    List<Review> findAllByNearestLocation(BigDecimal latitude, BigDecimal longitude, double radius);

    @Query("select r from Review r join r.location l join r.account a where r.isDeleted = false " +
            "and lower(concat(coalesce(l.name, ''), coalesce(l.description, ''), coalesce(l.phoneNumber, ''), coalesce(l.address, ''), " +
            "coalesce(r.description, ''), coalesce(r.rating, 0), " +
            "coalesce(a.email, ''), coalesce(a.fullName, ''))) like lower(concat('%', ?1, '%'))")
    Set<Review> searchByKeyword(String keyword);
}
