package com.felipe.Usuarios.service;

import com.felipe.Usuarios.entity.User;
import com.felipe.Usuarios.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUserByFirstName(String name) {
        return iUserRepository.findByFirstNameContainingIgnoreCase(name);
    }

    @Override
    public List<User> getAllUserOrderedByName() {
        return iUserRepository.findAllUsersOrderd();
    }

    @Override
    public List<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        if (id == null) {
            throw new RuntimeException("User id not found");
        }
        return iUserRepository.findById(id).orElseThrow();
    }

    @Override
    public User createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return iUserRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setPhone(user.getPhone());

        return iUserRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        iUserRepository.delete(existingUser);
    }
}