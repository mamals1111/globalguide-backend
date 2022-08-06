package com.myrmicatech.globalguidebackend.service.category;

import com.myrmicatech.globalguidebackend.entity.Category;
import com.myrmicatech.globalguidebackend.entity.Comment;
import com.myrmicatech.globalguidebackend.repository.CategoryRepository;
import com.myrmicatech.globalguidebackend.repository.CommentRepository;
import com.myrmicatech.globalguidebackend.service.comment.CommentService;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl extends GlobalGuideEntityServiceImpl<UUID, Category> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    @Override
    public List<Category> findAllByCategoryIds(List<UUID> categoryIds) {
        List<Category> categories = categoryRepository.findAllByIdContains(categoryIds);
        return categories;
    }

    @Override
    public List<Category> searchAndPaginate(String keyword, Pageable pageable) {
        List<Category> categories = categoryRepository.searchAndPaginate(keyword, pageable);
        return categories;
    }

    @Override
    public List<Category> search(String keyword) {
        List<Category> categories = categoryRepository.search(keyword);
        return categories;
    }
}
