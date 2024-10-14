package com.hackifytech.edu.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackifytech.edu.models.LearningContent;
import com.hackifytech.edu.models.responsebodies.LearningContentResponse;
import com.hackifytech.edu.repositories.LearningContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearningContentService {

    @Autowired
    private LearningContentRepository learningContentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<LearningContent> getAllLearningContents() {
        return learningContentRepository.findAll();
    }

    public Optional<LearningContent> getLearningContentById(Long id) {
        return learningContentRepository.findById(id);
    }
    
    public Optional<LearningContent> getLearningContentByTitle(String title) {
        return learningContentRepository.findByTitle(title);
    }

    public LearningContent createLearningContent(LearningContent learningContent) throws JsonProcessingException {
        if (learningContent.getJsonObject() != null) {
            learningContent.setJsonData(objectMapper.writeValueAsString(learningContent.getJsonObject()));
//            learningContent.setSlug(learningContent.getTitle().replace("-"," "));
            learningContent.setSlug(learningContent.getTitle().toLowerCase().replace(" ", "-"));
        }
        return learningContentRepository.save(learningContent);
    }

    public Optional<LearningContent> updateLearningContent(Long id, LearningContent updatedContent) throws JsonProcessingException {
        return learningContentRepository.findById(id).map(existingContent -> {
            existingContent.setTitle(updatedContent.getTitle());
            existingContent.setContentOrder(updatedContent.getContentOrder());
            existingContent.setSideParent(updatedContent.getSideParent());
            existingContent.setDeleted(updatedContent.isDeleted());
            existingContent.setApproved(updatedContent.isApproved());
            existingContent.setCategory(updatedContent.getCategory());

            if (updatedContent.getJsonObject() != null) {
                try {
                    existingContent.setJsonData(objectMapper.writeValueAsString(updatedContent.getJsonObject()));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error processing JSON data", e);
                }
            }

            return learningContentRepository.save(existingContent);
        });
    }

    public void deleteLearningContent(Long id) {
        learningContentRepository.deleteById(id);
    }
    
    public List<LearningContentResponse> getTitlesAndIdsByCategory(String categoryName) {
        List<Object[]> result = learningContentRepository.findIdAndTitleByCategory(categoryName);
        
        // Map the result to ContentResponse (DTO for id and title)
        return result.stream()
                .map(row -> new LearningContentResponse((Long) row[0], (String) row[1]))
                .collect(Collectors.toList());
    }
}