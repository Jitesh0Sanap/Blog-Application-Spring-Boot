package com.blog.blog_project.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long categoryId;
	
	@Column(name = "title" , length = 100 , nullable = false)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	private List<PostEntity> post;
}
