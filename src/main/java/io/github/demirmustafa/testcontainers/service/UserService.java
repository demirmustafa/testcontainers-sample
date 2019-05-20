package io.github.demirmustafa.testcontainers.service;

import io.github.demirmustafa.testcontainers.domain.entity.User;
import io.github.demirmustafa.testcontainers.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
