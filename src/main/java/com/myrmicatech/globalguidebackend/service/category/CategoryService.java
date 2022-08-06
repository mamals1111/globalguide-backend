package com.myrmicatech.globalguidebackend.service.category;

import com.myrmicatech.globalguidebackend.dto.CategoryDto;
import com.myrmicatech.globalguidebackend.entity.Category;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService extends GlobalGuideEntityService<UUID, Category> {
    List<Category> findAllByCategoryIds(List<UUID> categoryIds);

    List<Category> searchAndPaginate(String keyword, Pageable pageable);

    List<Category> search(String keyword);
}
