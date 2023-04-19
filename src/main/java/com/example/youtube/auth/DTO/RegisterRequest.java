package com.example.youtube.auth.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Username is null")
    @NotEmpty(message = "Username is empty")
    @NotBlank(message = "Username is blank")
    private String username;

    @NotNull(message = "email is null")
    @NotEmpty(message = "email is empty")
    @NotBlank(message = "email is blank")
    private String email;

    @NotNull(message = "password is null")
    @NotEmpty(message = "password is empty")
    @NotBlank(message = "password is blank")
    private String password;


    @NotNull(message = "userType is null")
    @NotEmpty(message = "userType is empty")
    @NotBlank(message = "userType is blank")
    private String userType;
}
