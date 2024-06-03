package com.dylabo.dydev.domain.notice.service.impl;

import com.dylabo.dydev.domain.notice.repository.NoticeRepository;
import com.dylabo.dydev.domain.notice.service.NoticeService;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDTO;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public NoticeResponseDTO getNoticeById(Long id) {
        return null;
    }

    @Override
    public Page<NoticeResponseDTO> getNoticeList(NoticeRequestDTO.Search search, Pageable pageable) {
        return null;
    }
}
