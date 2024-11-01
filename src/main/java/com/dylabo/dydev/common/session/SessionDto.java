package com.dylabo.dydev.common.session;

import com.dylabo.dydev.domain.user.enums.UserTypes;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SessionDto {

    private Boolean isAdmin;

    private Boolean isSuper;

    private String userId;

    private UserTypes userType;

    private String name;

    private String email;

    private LocalDateTime lastLoginDateTime;

}
