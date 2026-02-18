package com.example.library.service;

import com.example.library.dto.userDTO.CreateUserDTO;
import com.example.library.dto.userDTO.UserResponseDTO;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDTO createUser(CreateUserDTO dto) {
        if(userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    public List<UserResponseDTO> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }

    public UserResponseDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, CreateUserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        user.setName(dto.name());
        user.setEmail(dto.email());

        User userUpdate = userRepository.save(user);
        return new UserResponseDTO(
                userUpdate.getId(),
                userUpdate.getName(),
                userUpdate.getEmail()
        );
    }

    @Transactional
    public  void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        userRepository.delete(user);
    }
}

