package com.telusko.service;

import com.telusko.model.Image;
import com.telusko.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public Optional<Image> getImageById(Integer id) {
        return imageRepository.findById(id);
    }
}

