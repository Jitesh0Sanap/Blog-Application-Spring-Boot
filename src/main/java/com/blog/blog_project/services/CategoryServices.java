package com.blog.blog_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blog_project.payloads.CategoryDto;

@Service
public interface CategoryServices {
	
	CategoryDto creatCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

	CategoryDto getCategoryById(long categoryId);

	List<CategoryDto> getAllCategory();

	void deleteCategoryById(Long categoryId);
}
