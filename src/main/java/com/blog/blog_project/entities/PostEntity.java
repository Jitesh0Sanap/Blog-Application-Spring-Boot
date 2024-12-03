package com.blog.blog_project.entities;

import java.util.Date;
import java.util.Set;

import com.blog.blog_project.payloads.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "post_title" , length = 100)
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	@ManyToOne()
	private Category category;
	
	@ManyToOne()
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments;
}
