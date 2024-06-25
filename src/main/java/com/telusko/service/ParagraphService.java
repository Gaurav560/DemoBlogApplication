package com.telusko.service;

import com.telusko.model.Paragraph;
import com.telusko.repo.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParagraphService {
    @Autowired
    private ParagraphRepository paragraphRepository;

    public Paragraph saveParagraph(Paragraph paragraph) {
        return paragraphRepository.save(paragraph);
    }

    public Optional<Paragraph> getParagraphById(Integer id) {
        return paragraphRepository.findById(id);
    }
}
