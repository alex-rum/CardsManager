package com.cards.manager.configurations;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class RedisContainerInitializer {
    private static final int PORT = 6379;
    private static final String DOCKER_IMAGE = "redis:6.2.6";

    private static final GenericContainer REDIS_CONTAINER = new GenericContainer(DockerImageName.parse(DOCKER_IMAGE))
            .withExposedPorts(PORT)
            .withReuse(true);

    static {
        REDIS_CONTAINER.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(PORT));
    }
}
