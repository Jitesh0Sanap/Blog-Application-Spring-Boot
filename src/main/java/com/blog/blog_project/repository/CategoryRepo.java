package com.blog.blog_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blog_project.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
