package com.dylabo.dydev.domain.notice.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.notice.service.NoticeService;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDto;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_ADMIN + CommonApiUrls.API_PACKAGE_NOTICE)
public class AdminNoticeController {

    private final NoticeService noticeService;

    @PostMapping("")
    public ResponseEntity<NoticeResponseDto> doSetInsertNotice(
            @Validated @RequestBody NoticeRequestDto noticeRequestDto,
            BindingResult bindingResult) {
        return new ResponseEntity<>(noticeService.setInsertNotice(noticeRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeResponseDto> doSetUpdateNoticeById(
            @PathVariable("id") Long id,
            @Validated @RequestBody NoticeRequestDto noticeRequestDto,
            BindingResult bindingResult) {
        return new ResponseEntity<>(noticeService.setUpdateNotice(id, noticeRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> doSetDeleteNoticeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(noticeService.setDeleteNotice(id), HttpStatus.OK);
    }

}
