package com.dylabo.dydev;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class MyTest {

    @Test
    void myTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String pwd = passwordEncoder.encode("12134568");
        System.out.println(pwd);
    }

}
