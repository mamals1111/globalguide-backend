package com.myrmicatech.globalguidebackend.controller;

import com.myrmicatech.globalguidebackend.dto.ReactionDto;
import com.myrmicatech.globalguidebackend.dto.SearchAndPaginationDto;
import com.myrmicatech.globalguidebackend.entity.Category;
import com.myrmicatech.globalguidebackend.entity.Reaction;
import com.myrmicatech.globalguidebackend.service.category.CategoryService;
import com.myrmicatech.globalguidebackend.service.reaction.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, path = "findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<Category> categories = categoryService.list();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "findByKeyword")
    public ResponseEntity<Object> findByKeyword(@RequestParam("keyword") String keyword) {
        try {
//            int currentPage = searchAndPaginationDto.getPageNumber();
//            int pageSize = searchAndPaginationDto.getPageSize();
//            String keyword = searchAndPaginationDto.getKeyword();
//            Pageable accountPage = PageRequest.of(currentPage, pageSize);

//            Response<List<Loan>> response = new Response<>();
//            List<Loan> allLoans = loanService.findAll();
//            response.setData(loanList);
//            response.setPageSize(pageSize);
//            response.setPageNumber(currentPage);
//            response.setTotalPages(Math.ceil((double) allLoans.size() / pageSize));
//            response.setTotalRecords(allLoans.size());

            List<Category> categories = categoryService.search(keyword);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
