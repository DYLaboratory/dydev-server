package com.dylabo.dydev.domain.notice.service;

import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDto;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

    NoticeResponseDto getNoticeById(Long id);

    Page<NoticeResponseDto> getNoticeList(NoticeRequestDto.Search search, Pageable pageable);

    NoticeResponseDto setInsertNotice(NoticeRequestDto noticeRequestDto);

    NoticeResponseDto setUpdateNotice(Long id, NoticeRequestDto noticeRequestDto);

    Long setDeleteNotice(Long id);

}
