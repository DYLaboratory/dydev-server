package com.dylabo.dydev.common.session;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SessionDto {

    private String userId;

    private String name;

    private String email;

    private String lastLoginDateTime;

}
