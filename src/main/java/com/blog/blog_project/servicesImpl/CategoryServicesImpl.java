package com.blog.blog_project.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.blog_project.entities.Category;
import com.blog.blog_project.exception.ResourceNotfoundException;
import com.blog.blog_project.payloads.CategoryDto;
import com.blog.blog_project.repository.CategoryRepo;
import com.blog.blog_project.services.CategoryServices;

@Component
public class CategoryServicesImpl implements CategoryServices {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto creatCategory(CategoryDto categoryDto) {
		Category category = this.DtotoCategory(categoryDto);
		Category save = this.categoryRepo.save(category);
		return this.categoryToDto(save);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotfoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category save = categoryRepo.save(category);
		CategoryDto updatedCategoryDto = categoryToDto(save);
		return updatedCategoryDto;

	}

	@Override
	public CategoryDto getCategoryById(long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotfoundException("category", "categoryId", categoryId));
		CategoryDto categoryDto = this.categoryToDto(category);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> ListOfAllCategory = categoryRepo.findAll();
		List<CategoryDto> collectCategoyDto = ListOfAllCategory.stream().map(category -> categoryToDto(category))
				.collect(Collectors.toList());
		return collectCategoyDto;
	}

	@Override
	public void deleteCategoryById(Long categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotfoundException("category", "categoryId", categoryId));
		this.categoryRepo.deleteById(categoryId);
	}

	private Category DtotoCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}

	private CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
