package com.blog.blog_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_project.entities.Category;
import com.blog.blog_project.payloads.ApiResponce;
import com.blog.blog_project.payloads.CategoryDto;
import com.blog.blog_project.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryServices categoryServices;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> creatCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto creatCategory = categoryServices.creatCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(creatCategory , HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId){
		CategoryDto updateCategory = categoryServices.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory , HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId){
		CategoryDto categoryById = categoryServices.getCategoryById( categoryId );
		return new ResponseEntity<CategoryDto>(categoryById , HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = categoryServices.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponce> deleteCategoryById(@PathVariable Long categoryId){
		categoryServices.deleteCategoryById( categoryId );
		return new ResponseEntity<ApiResponce>(new ApiResponce("category delated successfully !!" , true) , HttpStatus.OK);
	}
}
