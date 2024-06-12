package com.dylabo.dydev.domain.auth.service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AuthDto {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

}
