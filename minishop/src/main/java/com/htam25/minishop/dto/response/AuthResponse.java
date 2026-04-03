package com.htam25.minishop.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type;
}
