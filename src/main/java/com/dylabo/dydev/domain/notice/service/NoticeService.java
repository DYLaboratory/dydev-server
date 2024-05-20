package com.dylabo.dydev.domain.notice.service;

import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDTO;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

    NoticeResponseDTO getNoticeById(Long id);

    Page<NoticeResponseDTO> getNoticeList(NoticeRequestDTO.Search search, Pageable pageable);

}
