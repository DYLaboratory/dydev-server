package com.dylabo.dydev.domain.auth.service.impl;

import com.dylabo.dydev.common.utils.SessionUtils;
import com.dylabo.dydev.domain.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Override
    public void signOut() {
        SessionUtils.clearSession();
    }

}
