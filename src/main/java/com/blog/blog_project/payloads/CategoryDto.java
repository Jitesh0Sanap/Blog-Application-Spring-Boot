package com.blog.blog_project.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	private long categoryId;

	@NotBlank
	@Size(min = 4 , message = "min size of desc is 4 !!")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10 , message = "min size of desc is 10 !!")
	private String categoryDescription;
}
