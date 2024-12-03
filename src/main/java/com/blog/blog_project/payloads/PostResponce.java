package com.blog.blog_project.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponce {
	
	List<PostDto> content;
	private int pageNumber;
	private long pageSize;
	private long totalElement;
	private long totalPages;
	
	private boolean lastPage;

}
