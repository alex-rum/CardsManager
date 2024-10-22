package com.cards.manager.configurations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.cards.manager")
public class TestApplicationConfiguration {
}
