package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.entity.ids.AccountReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReactionRepository extends JpaRepository<Reaction, UUID>, JpaSpecificationExecutor<Reaction> {

    Optional<Reaction> findByAccountReviewId(AccountReviewId accountReviewId);
}
