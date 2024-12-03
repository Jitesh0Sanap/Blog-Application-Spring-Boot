package com.blog.blog_project.servicesImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blog_project.services.FileServices;

@Component
public class FileServicesImpl implements FileServices {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {


		//file name
		String name = file.getOriginalFilename();
		//abc.png
		
		// random file name generate
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//full path 
		String filepath = path + File.separator + fileName1;
		
		//creat folder is not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filepath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream iStream = new FileInputStream(fullPath);
		return iStream;
	}

}
