package com.cards.manager.configurations.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Configuration
public class JacksonConfiguration {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)
                ;
    }
}
