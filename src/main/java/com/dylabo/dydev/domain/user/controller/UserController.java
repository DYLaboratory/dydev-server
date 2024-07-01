package com.dylabo.dydev.domain.user.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.user.service.UserService;
import com.dylabo.dydev.domain.user.service.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_USER)
public class UserController {

    private final UserService userService;

    @PutMapping("/password")
    public ResponseEntity<String> doSetUpdateLoginUserPassword(
            @Validated @RequestBody UserRequestDto.NewPassword requestDto,
            BindingResult bindingResult) {
        return new ResponseEntity<>(userService.setUpdateLoginUserPassword(requestDto), HttpStatus.OK);
    }

}
