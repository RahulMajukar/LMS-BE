package com.hackifytech.edu.config;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Create a JsonFactory with custom StreamReadConstraints
        JsonFactory jsonFactory = JsonFactory.builder()
                .streamReadConstraints(StreamReadConstraints.builder()
                        .maxNestingDepth(2000)  // Set max nesting depth for JSON reading
                        .build())
                .build();

        // Use the JsonFactory in the ObjectMapper
        return JsonMapper.builder(jsonFactory).build();
    }
}
