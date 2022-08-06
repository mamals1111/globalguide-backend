package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.File;
import com.myrmicatech.globalguidebackend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID>, JpaSpecificationExecutor<Location> {

    List<Location> findAllByLatitudeLessThanEqualAndLatitudeGreaterThanEqualAndLongitudeLessThanEqualAndLongitudeGreaterThanEqual
            (BigDecimal biggestLatitude, BigDecimal smallestLatitude, BigDecimal biggestLongitude, BigDecimal smallestLongitude);

    List<Location> findAllByAddress(String address);

    List<Location> findAllByLatitudeEqualsAndLongitudeEquals(BigDecimal latitude, BigDecimal longitude);

//    @Query("select l from Location l join l.account a where l.account = :#{#account} and l.latitude = :#{#latitude} and l.longitude = :#{#longitude} and l.isDeleted = false")
//    List<Location> findLocationByLatitudeAndLongitudeAndAccount(BigDecimal latitude, BigDecimal longitude, Account account);

    @Query("select l from Location l left join l.categories c where l.isDeleted = false and " +
            "lower(concat(coalesce(l.name, ''), coalesce(l.description, ''), coalesce(l.phoneNumber, ''), coalesce(l.address, ''), coalesce(c.name, ''), coalesce(c.description, ''))) like lower(concat('%', ?1, '%')) ")
    Set<Location> searchByKeyword(String keyword);

    @Query("select l from Location l where l.isDeleted = false " +
            "order by sqrt((l.latitude - ?1) * (l.latitude - ?1) + (l.longitude - ?2) * (l.longitude - ?2)) asc")
    List<Location> findNearestLocation(BigDecimal latitude, BigDecimal longitude);
}
