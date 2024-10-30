package com.dylabo.dydev.domain.history.repository;

import com.dylabo.dydev.domain.history.entity.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Long> {

    List<AccessHistory> findByAccessIpAndCreateDateTimeBetween(String accessIp, LocalDateTime startDate, LocalDateTime endDate);

}
