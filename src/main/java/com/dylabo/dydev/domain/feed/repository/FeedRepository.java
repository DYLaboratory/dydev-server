package com.dylabo.dydev.domain.feed.repository;

import com.dylabo.dydev.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    List<Feed> findByCreateDateTimeAfterOrderByCreateDateTimeDesc(LocalDateTime standardDateTime);

}
