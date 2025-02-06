package com.felipe.Usuarios.repository;

import com.felipe.Usuarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u ORDER BY u.firstName ASC")
  List<User> findAllUsersOrderd();

  List<User> findByFirstNameContainingIgnoreCase(String name);
}
