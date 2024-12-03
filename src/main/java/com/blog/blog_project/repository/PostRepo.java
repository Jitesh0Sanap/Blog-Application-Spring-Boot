package com.blog.blog_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog_project.entities.Category;
import com.blog.blog_project.entities.PostEntity;
import com.blog.blog_project.entities.User;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {
	
	List<PostEntity> findByUser(User user);
	List<PostEntity> findByCategory(Category category);
	
	List<PostEntity> findByTitleContaining(String title);
	 
}
