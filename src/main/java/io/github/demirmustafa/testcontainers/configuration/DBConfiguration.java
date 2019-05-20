package io.github.demirmustafa.testcontainers.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"io.github.demirmustafa.testcontainers.domain.repository"})
public class DBConfiguration {
}
