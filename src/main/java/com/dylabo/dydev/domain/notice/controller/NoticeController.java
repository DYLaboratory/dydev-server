package com.dylabo.dydev.domain.notice.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.notice.service.NoticeService;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDTO;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_NOTICE)
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> doGetNoticeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<NoticeResponseDTO>> doGetNoticeList(NoticeRequestDTO.Search search, Pageable pageable) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<NoticeResponseDTO> doSetInsertNotice(@RequestBody NoticeRequestDTO noticeRequestDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeResponseDTO> doSetUpdateNoticeById(
            @PathVariable("id") Long id,
            @RequestBody NoticeRequestDTO noticeRequestDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> doSetDeleteNoticeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
