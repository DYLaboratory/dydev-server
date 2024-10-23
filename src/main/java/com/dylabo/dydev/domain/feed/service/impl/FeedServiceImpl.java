package com.dylabo.dydev.domain.feed.service.impl;

import com.dylabo.core.common.exception.ApiException;
import com.dylabo.core.domain.base.dto.IdDto;
import com.dylabo.dydev.common.exception.DydevErrorMessage;
import com.dylabo.dydev.domain.feed.entity.Feed;
import com.dylabo.dydev.domain.feed.repository.FeedRepository;
import com.dylabo.dydev.domain.feed.service.FeedService;
import com.dylabo.dydev.domain.feed.service.dto.FeedRequestDto;
import com.dylabo.dydev.domain.feed.service.dto.FeedResponseDto;
import com.dylabo.dydev.domain.file.enums.FileTypes;
import com.dylabo.dydev.domain.file.service.FileService;
import com.dylabo.dydev.domain.file.service.dto.FileContentRequestDto;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    private final FileService fileService;

    private final ModelMapper modelMapper;

    // 피드 유효일 시 - 3일
    // TODO: 설정에서 기간 변경할 수 있도록 DB에 저장
    private final static LocalDateTime FEED_STANDARD_DATE_TIME = LocalDateTime.now().minusDays(3);

    private Feed getFeedEntityById(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> new ApiException(DydevErrorMessage.FEED_NOT_FOUND));
    }

    @Override
    public List<FeedResponseDto> getRecentFeedList() {
        return feedRepository.findByCreateDateTimeAfter(FEED_STANDARD_DATE_TIME).stream()
                .map(feed -> {
                    FeedResponseDto feedResponseDto = modelMapper.map(feed, FeedResponseDto.class);

                    // set file
                    List<FileContentResponseDto> fileList = fileService.getFileContentListByRelationId(FileTypes.FEED, feed.getId());
                    feedResponseDto.setFileList(fileList.stream()
                            .map(dto -> modelMapper.map(dto, FileContentResponseDto.FileInfoDto.class))
                            .collect(Collectors.toList()));

                    return feedResponseDto;
                })
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
    public FeedResponseDto setUpdateFeed(Long feedId, FeedRequestDto.UpdateDto feedRequestDto, List<MultipartFile> uploadFiles) {
        // get
        Feed feed = getFeedEntityById(feedId);

        // update
        modelMapper.map(feedRequestDto, feed);

        // file upload
        List<FileContentRequestDto.FileUploadDto> uploadList = new ArrayList<>();
        for (int i = 0; i < uploadFiles.size(); i++) {
            uploadList.add(FileContentRequestDto.FileUploadDto.builder()
                    .uploadFile(uploadFiles.get(i))
                    .seq(feedRequestDto.getInsertFiles().get(i).getSeq())
                    .build());
        }

        List<FileContentResponseDto> insertFiles = fileService.setUploadFilesWithSeq(FileTypes.FEED, uploadList, feedId);

        // file update
        List<FileContentResponseDto> updateFiles = fileService.setUpdateFileSeq(feedRequestDto.getUpdateFiles());

        // file delete
        fileService.setDeleteFilesById(feedRequestDto.getDeleteFiles().stream()
                .map(IdDto::getId)
                .collect(Collectors.toList())
        );

        // response
        FeedResponseDto feedResponseDto = modelMapper.map(feed, FeedResponseDto.class);

        // response - file
        updateFiles.addAll(insertFiles);
        Collections.sort(updateFiles); // sort by seq

        feedResponseDto.setFileList(updateFiles.stream()
                .map(file -> modelMapper.map(file, FileContentResponseDto.FileInfoDto.class))
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
