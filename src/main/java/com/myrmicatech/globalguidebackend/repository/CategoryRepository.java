package com.myrmicatech.globalguidebackend.repository;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {

    @Query("select c from Category c where c.id in ?1")
    List<Category> findAllByIdContains(List<UUID> categoryIds);

    @Query("select c from Category c where c.isDeleted = false " +
            "and lower(concat(coalesce(c.name, ''), coalesce(c.description, ''))) like lower(concat('%', ?1, '%'))")
    List<Category> searchAndPaginate(String keyword, Pageable pageable);

    @Query("select c from Category c where c.isDeleted = false " +
            "and lower(concat(coalesce(c.name, ''), coalesce(c.description, ''))) like lower(concat('%', ?1, '%'))")
    List<Category> search(String keyword);
}
