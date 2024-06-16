package com.dylabo.dydev.common.session;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_SESSION)
public class SessionController {

    private final SessionComponent sessionComponent;

    @GetMapping("/user")
    public ResponseEntity<SessionDto> doGetSession() {
        return new ResponseEntity<>(sessionComponent.getSession(), HttpStatus.OK);
    }

}
