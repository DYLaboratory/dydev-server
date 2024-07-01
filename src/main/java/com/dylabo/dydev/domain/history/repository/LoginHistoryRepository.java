package com.dylabo.dydev.domain.history.repository;

import com.dylabo.dydev.domain.history.entity.LoginHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

    @Query(value = "select distinct lh from LoginHistory lh " +
            "where lh.user.userId = :userId"
            , countQuery = "select count(distinct lh) from LoginHistory lh " +
            "where lh.user.userId = :userId")
    Page<LoginHistory> findLoginHistoriesByUserIdAndPaging(@Param("userId") String userId, Pageable pageable);

}
