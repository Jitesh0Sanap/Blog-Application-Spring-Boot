package com.blog.blog_project.services;

import org.springframework.stereotype.Service;

import com.blog.blog_project.payloads.CommentDto;

@Service
public interface CommentServices {
	
	CommentDto createCommentDto(CommentDto commentDto , long id);
	void deleteComment(long id);
	
}
