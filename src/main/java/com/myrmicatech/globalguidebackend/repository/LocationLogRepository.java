package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.LocationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LocationLogRepository extends JpaRepository<LocationLog, UUID>, JpaSpecificationExecutor<LocationLog> {
}
