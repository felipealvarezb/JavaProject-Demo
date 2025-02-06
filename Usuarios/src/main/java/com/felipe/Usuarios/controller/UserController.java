package com.felipe.Usuarios.controller;

import com.felipe.Usuarios.dto.CreateUserDTO;
import com.felipe.Usuarios.dto.UpdateUserDTO;
import com.felipe.Usuarios.dto.UserResponseDTO;
import com.felipe.Usuarios.entity.User;
import com.felipe.Usuarios.mapper.IUserMapper;
import com.felipe.Usuarios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserMapper iUserMapper;

    @GetMapping(value = "/name")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersByName(@RequestParam(required = false) String name) {
        List<User> userList = iUserService.getUserByFirstName(name);

        List<UserResponseDTO> userResponseDTOList = iUserMapper.listUserToListUSerResponseDTO(userList);

        return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping(value = "/ordered")
    public ResponseEntity<List<UserResponseDTO>> getAllOrderBy() {
        List<User> userList = iUserService.getAllUserOrderedByName();

        List<UserResponseDTO> userResponseDTOList = iUserMapper.listUserToListUSerResponseDTO(userList);

        return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<User> userList = iUserService.getAllUsers();

        List<UserResponseDTO> userResponseDTOList = iUserMapper.listUserToListUSerResponseDTO(userList);

        return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable("userId") Long id) {
        User user = iUserService.getUserById(id);

        UserResponseDTO userResponseDTO = iUserMapper.userToUserResponseDto(user);

        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@Validated @RequestBody CreateUserDTO createUserDTO) {
        User user = iUserMapper.createUserDtoToUser(createUserDTO);

        User savedUser = iUserService.createUser(user);

        UserResponseDTO userResponseDTO = iUserMapper.userToUserResponseDto(savedUser);

        return  ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("userId") Long id, @RequestBody UpdateUserDTO updateUserDTO){
        User updatedUser = iUserMapper.updateUserDtoToUser(updateUserDTO);

        User user = iUserService.updateUser( id, updatedUser);

        UserResponseDTO userResponseDTO = iUserMapper.userToUserResponseDto(user);

        return  ResponseEntity.ok().body(userResponseDTO);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") Long id) {
        iUserService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
