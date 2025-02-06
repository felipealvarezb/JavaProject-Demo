package com.felipe.Usuarios.service;

import com.felipe.Usuarios.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getUserByFirstName(String name);

    List<User> getAllUserOrderedByName();

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUserById(Long id);
}
