package com.example.backend.dtos;

import com.example.backend.models.Staff;
import com.example.backend.models.User;
import com.example.backend.models.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonPropertyOrder({
        "id",
        "username",
        "password",
        "roles",
        "staffInfo",
        "trainerInfo",
        "memberInfo"
})
public class UserDTO {

    @JsonProperty("id")
    Long id;

    @JsonProperty("username")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    String username;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password;

    @JsonProperty("roles")
    UserRole roles;

    MemberDTO memberInfo;
    TrainerDTO trainerInfo;
    StaffDTO staffInfo;

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles(),
                user.getMemberInfo() != null ? MemberDTO.fromEntity(user.getMemberInfo()) : null,
                user.getTrainerInfo() != null ? TrainerDTO.fromEntity(user.getTrainerInfo()) : null,
                user.getStaffInfo() != null ? StaffDTO.fromEntity(user.getStaffInfo()) : null
        );
    }
}
