package com.example.modelsviewerweb.services.security;

import com.example.modelsviewerweb.dto.security.UserRegistrationDto;
import com.example.modelsviewerweb.entities.security.Watcher;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Watcher save(UserRegistrationDto registrationDto);
}
