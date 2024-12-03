package com.blog.blog_project.payloads;

import java.util.Date;
import java.util.Set;

import com.blog.blog_project.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private long id;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments;

}
