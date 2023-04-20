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
    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    private String username;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    private String email;

    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    private String password;


    @NotNull(message = "null")
    @NotEmpty(message = "empty")
    @NotBlank(message = "blank")
    private String userType;
}
