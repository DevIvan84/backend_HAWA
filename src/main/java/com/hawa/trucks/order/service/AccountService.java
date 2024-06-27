package com.hawa.trucks.order.service;

import com.hawa.trucks.order.dto.SignupFormDTO;
import com.hawa.trucks.order.dto.UserProfileDTO;
import com.hawa.trucks.order.entity.User;
import com.hawa.trucks.order.exceptions.BadRequestException;
import com.hawa.trucks.order.exceptions.ResourceNotFoundException;
import com.hawa.trucks.order.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class AccountService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public UserProfileDTO signup(SignupFormDTO signupFormDTO) {
        boolean emailAlreadyExists = userRepository.existsByEmail(signupFormDTO.getEmail());

        if (emailAlreadyExists) {
            throw new BadRequestException("El email ya está siendo usado por otro usuario.");
        }

        User user = modelMapper.map(signupFormDTO, User.class);
        user.setPassword(passwordEncoder.encode(signupFormDTO.getPassword()));
        user.setRole(User.Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return modelMapper.map(user, UserProfileDTO.class);
    }

    public UserProfileDTO findByEmail(String email) {
        User user = userRepository
                .findOneByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);

        return modelMapper.map(user, UserProfileDTO.class);
    }

}

