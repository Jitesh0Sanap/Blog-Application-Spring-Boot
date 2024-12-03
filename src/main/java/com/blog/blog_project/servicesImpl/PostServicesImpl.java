package com.blog.blog_project.servicesImpl;

import java.nio.channels.NonReadableChannelException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.security.MD5Encoder;
import org.hibernate.annotations.Collate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.blog.blog_project.entities.Category;
import com.blog.blog_project.entities.PostEntity;
import com.blog.blog_project.entities.User;
import com.blog.blog_project.exception.ResourceNotfoundException;
import com.blog.blog_project.payloads.PostDto;
import com.blog.blog_project.payloads.PostResponce;
import com.blog.blog_project.repository.CategoryRepo;
import com.blog.blog_project.repository.PostRepo;
import com.blog.blog_project.repository.UserRepo;
import com.blog.blog_project.services.PostServices;

@Component
public class PostServicesImpl implements PostServices {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto creatPost(PostDto postDto, long id, long categoryId) {

		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("User", "UserId", id));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotfoundException("Category", "categoryId", categoryId));

		PostEntity post = this.modelMapper.map(postDto, PostEntity.class);
		post.setImageName("default,png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		PostEntity newPost = postRepo.save(post);
		PostDto newPostDto = modelMapper.map(newPost, PostDto.class);
		return newPostDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		PostEntity post = postRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("Post", "PostId", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		PostEntity updatedPost = postRepo.save(post);
		PostDto updatedPostdto = modelMapper.map(updatedPost, PostDto.class);
		return updatedPostdto;
	}

	@Override
	public void deletePostById(Long id) {
		PostEntity post = postRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("Post", "PostId", id));
		postRepo.deleteById(id);
		
	}

	@Override
	public PostDto getPostById(long id) {
		PostEntity post = postRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("Post", "PostId", id));
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber , Integer pageSize , String sortBy) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize , Sort.by(sortBy));
		
		 Page<PostEntity> pagePost = postRepo.findAll(p);
		 List<PostEntity> allPost = pagePost.getContent();
		 
		List<PostDto> collectAllPostDtos = allPost.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponce postResponce = new PostResponce();
		postResponce.setContent(collectAllPostDtos);
		postResponce.setPageNumber(pagePost.getNumber());
		postResponce.setPageSize(pagePost.getSize());
		postResponce.setTotalElement(pagePost.getTotalElements());
		postResponce.setTotalPages(pagePost.getTotalPages());
		postResponce.setLastPage(pagePost.isLast());
		
		return postResponce;
	}

	@Override
	public List<PostDto> getPostByCategoey(long categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotfoundException("Category", "categoryId", categoryId));
		List<PostEntity> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotfoundException("User", "UserId", id));
		List<PostEntity> postByUser = postRepo.findByUser(user);
		List<PostDto> postDtos = postByUser.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getSearchPost(String keywords) {
		 List<PostEntity> byTitleContaining = postRepo.findByTitleContaining(keywords);
		 List<PostDto> postDtos = byTitleContaining.stream().map((p) -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	
}
