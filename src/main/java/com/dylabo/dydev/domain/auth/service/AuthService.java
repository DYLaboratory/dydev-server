package com.dylabo.dydev.domain.auth.service;

import com.dylabo.dydev.common.session.SessionDto;
import com.dylabo.dydev.domain.auth.service.dto.AuthDto;

public interface AuthService {

    SessionDto signIn(AuthDto authDto);

    String signOut();

}
