package com.example.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationDTO {
    String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    String token;
    String refreshToken;

    boolean authenticated;
}
