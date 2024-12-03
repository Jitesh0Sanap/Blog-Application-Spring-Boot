package com.blog.blog_project.exception;

import lombok.Getter;
import lombok.Setter;

 
@Getter
@Setter
public class UsernameNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public UsernameNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}

