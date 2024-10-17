package com.dylabo.dydev.domain.feed.service.impl;

import com.dylabo.dydev.domain.feed.entity.Feed;
import com.dylabo.dydev.domain.feed.repository.FeedRepository;
import com.dylabo.dydev.domain.feed.service.FeedService;
import com.dylabo.dydev.domain.feed.service.dto.FeedRequestDto;
import com.dylabo.dydev.domain.feed.service.dto.FeedResponseDto;
import com.dylabo.dydev.domain.file.enums.FileTypes;
import com.dylabo.dydev.domain.file.service.FileService;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    private final FileService fileService;

    private final ModelMapper modelMapper;

    // 피드 유효일 시 - 3일
    private final static LocalDateTime FEED_STANDARD_DATE_TIME = LocalDateTime.now().minusDays(3);

    @Override
    public List<FeedResponseDto> getRecentFeedList() {
        return feedRepository.findByCreateDateTimeAfter(FEED_STANDARD_DATE_TIME).stream()
                .map(feed -> modelMapper.map(feed, FeedResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeedResponseDto setInsertFeed(FeedRequestDto feedRequestDto, List<MultipartFile> uploadFiles) {
        // save feed
        Feed feed = feedRepository.save(
                modelMapper.map(feedRequestDto, Feed.class)
        );

        // upload files
        List<FileContentResponseDto> fileList = fileService.setUploadFiles(FileTypes.FEED, uploadFiles, feed.getId());

        // response
        FeedResponseDto feedResponseDto = modelMapper.map(feed, FeedResponseDto.class);

        feedResponseDto.setFileList(fileList.stream()
                .map(f -> modelMapper.map(f, FileContentResponseDto.FileInfoDto.class))
                .collect(Collectors.toList())
        );

        return feedResponseDto;
    }

    @Override
    @Transactional
    public Long setDeleteFeed(Long id) {
        // delete from db
        feedRepository.deleteById(id);

        // delete files
        fileService.setDeleteFilesByRelation(FileTypes.FEED, id);

        return id;
    }
}
