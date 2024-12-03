package com.blog.blog_project.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotfoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	Long fieldValue;
	public ResourceNotfoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	 
	
}
