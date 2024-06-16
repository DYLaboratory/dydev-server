package com.dylabo.dydev.domain.notice.repository;

import com.dylabo.dydev.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = "select n from Notice n "
            + "where 1=1 "
            + "order by n.createDateTime desc")
    List<Notice> findAllBySort();

}
