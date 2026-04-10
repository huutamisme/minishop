package com.htam25.minishop.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "cannot be empty")
    private String username;

    @Email(message = "invalid format")
    @NotBlank(message = "cannot be empty")
    private String email;

    @Size(min = 6, message = "must be more than 5 characters")
    private String password;
}
