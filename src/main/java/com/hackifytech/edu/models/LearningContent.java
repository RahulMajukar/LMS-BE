package com.hackifytech.edu.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class LearningContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String title;
    private String slug;
    private String contentOrder;
    private String sideParent;
    private boolean isDeleted = false;
    private boolean isApproved = false;

    @Column(columnDefinition = "JSON")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jsonData;

    @Transient
    private JsonNode jsonObject;

    @ManyToOne
    @JoinColumn(name = "learningcat_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LearningCategory category;

    @PostLoad
    public void postLoad() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.jsonObject = mapper.readTree(this.jsonData);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing jsonData", e);
        }
    }

    @JsonIgnore
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}