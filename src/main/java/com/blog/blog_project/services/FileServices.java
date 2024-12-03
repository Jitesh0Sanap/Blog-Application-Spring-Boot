package com.blog.blog_project.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.websocket.server.ServerEndpoint;

@Service
public interface FileServices {
	
	String uploadImage(String path , MultipartFile file) throws IOException;
	InputStream getResource(String path , String fileName) throws FileNotFoundException;
}
