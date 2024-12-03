package com.blog.blog_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_project.payloads.ApiResponce;
import com.blog.blog_project.payloads.CommentDto;
import com.blog.blog_project.services.CommentServices;

@RestController
@RequestMapping("/comment")
public class CommentController {

	
	@Autowired
	private CommentServices commentServices;
	
	@PostMapping("/create/{id}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable long id){
		CommentDto createComment = commentServices.createCommentDto(commentDto, id);
		return new ResponseEntity<CommentDto>(createComment , HttpStatus.CREATED);
	}
	
	public ResponseEntity<ApiResponce> deleteComment(@PathVariable long id){
		 commentServices.deleteComment(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment deleted successfully" , true), HttpStatus.OK);
	}
}
