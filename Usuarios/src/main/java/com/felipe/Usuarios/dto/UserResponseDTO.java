package com.felipe.Usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private boolean isActive;

    private Date createdAt;

    private Date updatedAt;
}
