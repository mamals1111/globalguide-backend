package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.entity.File;
import com.myrmicatech.globalguidebackend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID>, JpaSpecificationExecutor<File> {

    @Query("select f from File f join f.locationBanner where f.locationBanner = :#{#location}")
    List<File> findAllByLocationBanner(Location location);
}
