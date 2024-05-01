package com.study.board.controller;

import com.study.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<String> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories"; // categories.html 템플릿을 렌더링
    }

    @GetMapping("/categories/{category}/stores")
    public String showStoresByCategory(@PathVariable String category, Model model) {
        List<String> stores = categoryService.getStoresByCategory(category);
        model.addAttribute("stores", stores);
        model.addAttribute("selectedCategory", category);
        return "stores"; // stores.html 템플릿을 렌더링
    }
}