package com.dylabo.dydev.domain.notice.service;

import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDto;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDto;

import java.util.List;

public interface NoticeService {

    NoticeResponseDto getNoticeById(Long id);

    List<NoticeResponseDto> getNoticeList(NoticeRequestDto.Search search);

    NoticeResponseDto setInsertNotice(NoticeRequestDto noticeRequestDto);

    NoticeResponseDto setUpdateNotice(Long id, NoticeRequestDto noticeRequestDto);

    Long setDeleteNotice(Long id);

}
