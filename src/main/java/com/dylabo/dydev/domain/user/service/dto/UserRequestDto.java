package com.dylabo.dydev.domain.user.service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserRequestDto extends UserDto {

    @Getter
    public static class NewPassword {
        @NotEmpty
        private String password;

        @NotEmpty
        private String newPassword;
    }

}
