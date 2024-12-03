package com.blog.blog_project.controller;

 
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blog_project.config.AppConstant;
import com.blog.blog_project.payloads.ApiResponce;
import com.blog.blog_project.payloads.PostDto;
import com.blog.blog_project.payloads.PostResponce;
import com.blog.blog_project.services.FileServices;
import com.blog.blog_project.services.PostServices;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/postapi")
public class PostController {

	@Autowired
	private PostServices postServices;
	
	@Autowired
	private FileServices fileServices;
	
	@Value("${project.image}")
	private String path;

	// create post

	@PostMapping("/user/{id}/category/{categoryId}/post")
	public ResponseEntity<PostDto> creatPost(@RequestBody PostDto postDto, @PathVariable long id,
			@PathVariable long categoryId) {
		PostDto creatPost = postServices.creatPost(postDto, id, categoryId);
		return new ResponseEntity<PostDto>(creatPost, HttpStatus.CREATED);

	}

	// update post

	@PutMapping("/updatePost/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
		PostDto updatePost = postServices.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// delet post

	@DeleteMapping("/deletePost/{id}")
	public ResponseEntity<ApiResponce> deletePost(@PathVariable long id) {
		postServices.deletePostById(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("post is successfully deleted ", true), HttpStatus.OK);
	}

	// get post by id

	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
		PostDto postById = postServices.getPostById(id);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	// getAllPost

	@GetMapping("/posts")
	public ResponseEntity<PostResponce> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy) {
		PostResponce allPost = postServices.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponce>(allPost, HttpStatus.OK);
	}

	// get post by category

	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostBycategory(@PathVariable long categoryId) {
		List<PostDto> postByCategoey = postServices.getPostByCategoey(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategoey, HttpStatus.OK);
	}

	// get post by user

	@GetMapping("/user/{id}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable long id) {
		List<PostDto> postByUser = postServices.getPostByUser(id);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}

	// post search by keywords

	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDto> searchPost = postServices.getSearchPost(keywords);
		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);
	}
	
	//post iamge upload
	@PostMapping("/post/image/upload/{id}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam MultipartFile image , @PathVariable long id) throws IOException{
		PostDto postDto = postServices.getPostById(id);
		String fileName = fileServices.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = postServices.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost , HttpStatus.OK);
		
	}
	
	//methos to serve file
	@GetMapping(value = "post/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	 public void DownloadImage(@PathVariable String imageName , HttpServletResponse response) throws IOException {
		
		InputStream resource = fileServices.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
}
