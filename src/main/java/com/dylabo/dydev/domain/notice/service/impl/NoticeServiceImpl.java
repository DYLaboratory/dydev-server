package com.dylabo.dydev.domain.notice.service.impl;

import com.dylabo.core.common.exception.ApiException;
import com.dylabo.dydev.common.exception.DydevErrorMessage;
import com.dylabo.dydev.domain.notice.entity.Notice;
import com.dylabo.dydev.domain.notice.repository.NoticeRepository;
import com.dylabo.dydev.domain.notice.service.NoticeService;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDto;
import com.dylabo.dydev.domain.notice.service.dto.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final ModelMapper modelMapper;

    private Notice getNoticeEntityById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new ApiException(DydevErrorMessage.NOTICE_NOT_FOUND));
    }

    @Override
    public NoticeResponseDto getNoticeById(Long id) {
        return modelMapper.map(getNoticeEntityById(id), NoticeResponseDto.class);
    }

    @Override
    public List<NoticeResponseDto> getNoticeList(NoticeRequestDto.Search search) {
        List<Notice> noticeList = noticeRepository.findAllBySort();

        return noticeList.stream()
                .map(notice -> modelMapper.map(notice, NoticeResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NoticeResponseDto setInsertNotice(NoticeRequestDto noticeRequestDto) {
        Notice notice = noticeRepository.save(
                modelMapper.map(noticeRequestDto, Notice.class)
        );

        return modelMapper.map(notice, NoticeResponseDto.class);
    }

    @Override
    @Transactional
    public NoticeResponseDto setUpdateNotice(Long id, NoticeRequestDto noticeRequestDto) {
        Notice notice = getNoticeEntityById(id);

        modelMapper.map(noticeRequestDto, notice);

        return modelMapper.map(notice, NoticeResponseDto.class);
    }

    @Override
    @Transactional
    public Long setDeleteNotice(Long id) {
        noticeRepository.deleteById(id);

        return id;
    }
}
