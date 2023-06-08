package com.grc.core.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

	String saveImage(MultipartFile imageFile) throws IOException;
}
