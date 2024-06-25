package com.telusko.controller;

import com.telusko.model.Blog;
import com.telusko.model.Paragraph;
import com.telusko.service.BlogService;
import com.telusko.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paragraphs")
public class ParagraphController {
    @Autowired
    private ParagraphService paragraphService;

    @Autowired
    private BlogService blogService;

    @PostMapping("/{blogId}")
    public Paragraph addParagraphToBlog(@PathVariable Integer blogId, @RequestBody Paragraph paragraph) {
        Blog blog = blogService.getBlogById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        paragraph.setBlog(blog);
        return paragraphService.saveParagraph(paragraph);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paragraph> getParagraph(@PathVariable Integer id) {
        return paragraphService.getParagraphById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

