package com.dylabo.dydev.domain.user.service.dto;

import com.dylabo.core.common.validate.PatternDefine;
import com.dylabo.core.domain.base.dto.BaseCUDto;
import com.dylabo.dydev.domain.user.enums.UserTypes;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDto extends BaseCUDto {

    @NotEmpty
    @Size(min = 5, max = 30)
    private String userId;

    @NotNull
    private UserTypes userType;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Pattern(regexp = PatternDefine.PASSWORD_NUMBER_PATTERN)
    @Size(max = 20)
    private String password;

    @NotEmpty
    @Pattern(regexp = PatternDefine.EMAIL_PATTERN)
    @Size(max = 200)
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer loginAttemptCount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastLoginDateTime;

}
