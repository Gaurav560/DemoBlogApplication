package com.telusko.service;

import com.telusko.model.Blog;
import com.telusko.repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Optional<Blog> getBlogById(Integer id) {
        return blogRepository.findById(id);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
