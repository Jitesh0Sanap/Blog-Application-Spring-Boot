package com.blog.blog_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blog_project.entities.PostEntity;
import com.blog.blog_project.entities.User;
import com.blog.blog_project.payloads.PostDto;
import com.blog.blog_project.payloads.PostResponce;

@Service
public interface PostServices {
	
	PostDto creatPost(PostDto postDto , long id , long categoryId);

	PostDto updatePost(PostDto postDto, Long id);
	
	void deletePostById(Long id);

	PostDto getPostById(long id);

	PostResponce getAllPost(Integer pageNumber , Integer pageSize , String sortBy);
	
	List<PostDto> getPostByCategoey(long categoryId);
	
	List<PostDto> getPostByUser(long id);
	
	List<PostDto> getSearchPost(String keywords);

	
}
