package com.dylabo.dydev.domain.feed.service.impl;

import com.dylabo.dydev.domain.feed.entity.Feed;
import com.dylabo.dydev.domain.feed.repository.FeedRepository;
import com.dylabo.dydev.domain.feed.service.FeedService;
import com.dylabo.dydev.domain.feed.service.dto.FeedRequestDto;
import com.dylabo.dydev.domain.feed.service.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

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
    public FeedResponseDto setInsertFeed(FeedRequestDto feedRequestDto) {
        Feed feed = feedRepository.save(
                modelMapper.map(feedRequestDto, Feed.class)
        );

        return modelMapper.map(feed, FeedResponseDto.class);
    }

    @Override
    @Transactional
    public Long setDeleteFeed(Long id) {
        feedRepository.deleteById(id);

        return id;
    }
}
