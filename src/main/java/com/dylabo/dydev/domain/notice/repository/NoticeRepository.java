package com.dylabo.dydev.domain.notice.repository;

import com.dylabo.dydev.domain.notice.entity.Notice;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = "select n from Notice n "
            + "where 1=1"
            , countQuery = "select count(n) from Notice n "
            + "where 1=1"
    )
    Page<Notice> findNoticeListWithSearchAndPaging(@Param("search") NoticeRequestDto.Search search, Pageable pageable);

}
