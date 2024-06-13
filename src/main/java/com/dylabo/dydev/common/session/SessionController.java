package com.dylabo.dydev.common.session;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.common.utils.SessionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonApiUrls.API_PACKAGE_SESSION)
public class SessionController {

    @GetMapping("/user")
    public ResponseEntity<SessionDto> doGetSession() {
        return new ResponseEntity<>(SessionUtils.getSession(), HttpStatus.OK);
    }

}
