package com.dylabo.dydev.domain.notice.repository;

import com.dylabo.dydev.domain.notice.entity.Notice;
import com.dylabo.dydev.domain.notice.service.dto.NoticeRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = "select distinct n from Notice n "
            + "where 1=1"
            , countQuery = "select count(distinct n) from Notice n "
            + "where 1=1"
    )
    Page<Notice> findNoticeWithSearchAndPaging(@Param("search") NoticeRequestDTO.Search search, Pageable pageable);

}
