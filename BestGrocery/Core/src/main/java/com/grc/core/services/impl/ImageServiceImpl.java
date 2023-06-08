package com.grc.core.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.grc.core.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public String saveImage(MultipartFile imageFile) throws IOException {
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		String dir = "src/main/webapp/resources/images/";
		Path path = Paths.get(dir+fileName);
		try(InputStream inputStream = imageFile.getInputStream()){
			Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
		}
		return fileName;
	}

}
