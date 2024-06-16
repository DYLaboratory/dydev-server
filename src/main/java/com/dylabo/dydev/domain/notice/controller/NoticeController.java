package com.dylabo.dydev.domain.notice.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.notice.service.NoticeService;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDto;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_NOTICE)
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponseDto> doGetNoticeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(noticeService.getNoticeById(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoticeResponseDto>> doGetNoticeList(NoticeRequestDto.Search search) {
        return new ResponseEntity<>(noticeService.getNoticeList(search), HttpStatus.OK);
    }

}
