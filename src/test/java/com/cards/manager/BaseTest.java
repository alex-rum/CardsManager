package com.cards.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cards.manager.configurations.RedisContainerInitializer;
import com.cards.manager.configurations.TestApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
public abstract class BaseTest extends RedisContainerInitializer {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
}
