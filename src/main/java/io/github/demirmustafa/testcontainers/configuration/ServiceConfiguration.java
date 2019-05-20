package io.github.demirmustafa.testcontainers.configuration;

import io.github.demirmustafa.testcontainers.domain.repository.UserRepository;
import io.github.demirmustafa.testcontainers.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
