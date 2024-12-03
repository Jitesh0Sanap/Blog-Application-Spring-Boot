package com.blog.blog_project.servicesImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blog.blog_project.entities.Comment;
import com.blog.blog_project.entities.PostEntity;
import com.blog.blog_project.exception.ResourceNotfoundException;
import com.blog.blog_project.payloads.CommentDto;
import com.blog.blog_project.repository.CommentRepo;
import com.blog.blog_project.repository.PostRepo;
import com.blog.blog_project.services.CommentServices;

@Component
public class CommentServiceImpl implements CommentServices{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createCommentDto(CommentDto commentDto, long id) {
		PostEntity post = postRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("post", "postId", id));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment saveCommnet = commentRepo.save(comment);
		return modelMapper.map(saveCommnet, CommentDto.class);
	}

	@Override
	public void deleteComment(long id) {
		Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("comment", "commentId", id));
		commentRepo.delete(comment);
	}

}
