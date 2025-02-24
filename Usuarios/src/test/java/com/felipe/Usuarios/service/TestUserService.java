package com.felipe.Usuarios.service;

import com.felipe.Usuarios.entity.User;
import com.felipe.Usuarios.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

  @Mock
  private IUserRepository iUserRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserServiceImpl iUserService;

  private User user1;
  private User user2;

  private List<User> mockUsers;

  @BeforeEach
  void setUp() {
    user1 = new User();
    user1.setUserId(1L);
    user1.setFirstName("John");
    user1.setLastName("Doe");
    user1.setEmail("0dJ3l@example.com");
    user1.setPassword("password123");
    user1.setActive(true);

    user2 = new User();
    user2.setUserId(2L);
    user2.setFirstName("Jane");
    user2.setLastName("Doe");
    user2.setEmail("cFp9F@example.com");
    user2.setPassword("password456");
    user2.setActive(true);

    mockUsers = Arrays.asList(user1, user2);
  }

  @Test
  void testGetUserByFirstName() {

    String searchName = "John";
    // Simulamos la respuesta del repositorio
    when(iUserRepository.findByFirstNameContainingIgnoreCase(searchName)).thenReturn(List.of(user1));

    // Ejecutamos el método del servicio
    List<User> result = iUserService.getUserByFirstName(searchName);

    // Verificaciones
    verify(iUserRepository, times(1)).findByFirstNameContainingIgnoreCase(searchName);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("John", result.get(0).getFirstName());
  }

  @Test
  void testGetAllUsers() {
    // Simulamos la respuesta del repositorio
    when(iUserRepository.findAll()).thenReturn(mockUsers);

    // Ejecutamos el método del servicio
    List<User> result = iUserService.getAllUsers();

    // Verificaciones
    verify(iUserRepository).findAll();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Jane", result.get(1).getFirstName());
  }

  @Test
  void testGetUserById() {
    // Simulamos la respuesta del repositorio
    when(iUserRepository.findById(1L)).thenReturn(Optional.of(user1));

    // Ejecutamos el método del servicio
    User result = iUserService.getUserById(1L);

    // Verificaciones
    verify(iUserRepository).findById(1L);
    assertNotNull(result);
    assertEquals("John", result.getFirstName());
  }

  @Test
  void testCreateUser() {

    //Simulación de la encriptación de la contraseña
    String encodedPassword = "$2a$10$encryptedpassword";
    when(passwordEncoder.encode(user1.getPassword())).thenReturn(encodedPassword);

    // Simulación del guardado del usuario en el repositorio
    when(iUserRepository.save(any(User.class))).thenAnswer(invocation -> {
      User savedUser = invocation.getArgument(0);
      savedUser.setUserId(1L); // Simula que la BD le asigna un ID
      return savedUser;
    });

    // Ejecutamos el método del servicio
    User createdUser = iUserService.createUser(user1);

    // Verificaciones
    verify(passwordEncoder, times(1)).encode("password123");
    verify(iUserRepository, times(1)).save(any(User.class));

    assertNotNull(createdUser);
    assertEquals(1L, createdUser.getUserId());
    assertEquals("John", createdUser.getFirstName());
    assertEquals("0dJ3l@example.com", createdUser.getEmail());
    assertEquals(encodedPassword, createdUser.getPassword());
  }
}
