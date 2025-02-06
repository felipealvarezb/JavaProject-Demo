package com.felipe.Usuarios.mapper;

import com.felipe.Usuarios.dto.CreateUserDTO;
import com.felipe.Usuarios.dto.UpdateUserDTO;
import com.felipe.Usuarios.dto.UserResponseDTO;
import com.felipe.Usuarios.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IUserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User createUserDtoToUser(CreateUserDTO createUserDTO);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User updateUserDtoToUser(UpdateUserDTO updateUserDTO);

    UserResponseDTO userToUserResponseDto(User user);

    List<UserResponseDTO> listUserToListUSerResponseDTO(List<User> userList);
}
