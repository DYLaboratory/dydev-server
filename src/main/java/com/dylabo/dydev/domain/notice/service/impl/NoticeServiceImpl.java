package com.dylabo.dydev.domain.notice.service.impl;

import com.dylabo.dydev.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl {

    private final NoticeRepository noticeRepository;

}
