package com.telusko.controller;

import com.telusko.model.Image;
import com.telusko.model.Paragraph;
import com.telusko.service.ImageService;
import com.telusko.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ParagraphService paragraphService;

    @PostMapping("/{paragraphId}")
    public Image addImageToParagraph(@PathVariable Integer paragraphId, @RequestParam("file") MultipartFile file) throws IOException {
        Paragraph paragraph = paragraphService.getParagraphById(paragraphId)
                .orElseThrow(() -> new RuntimeException("Paragraph not found"));


        String imageUrl = saveImageToFileSystem(file); // Save the image to the file system

        Image image = new Image();
        image.setImageUrl(imageUrl);
        image.setParagraph(paragraph);

        return imageService.saveImage(image);
    }



    // Method to save image to file system
    private String saveImageToFileSystem(MultipartFile file) throws IOException {
        // Get the absolute path to the images directory at the root of your application
        String imagesDir = Paths.get("src/main/resources/static/images").toAbsolutePath().toString();

        // Create the directory if it doesn't exist
        new File(imagesDir).mkdirs();

        // Save the image to the images directory and get the relative path
        Path path = Paths.get(imagesDir, file.getOriginalFilename());
        Files.write(path, file.getBytes());

        // Return the relative URL
        return "/images/" + file.getOriginalFilename();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Integer id) {
        return imageService.getImageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

