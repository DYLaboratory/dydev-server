package com.dylabo.dydev.domain.history.service.impl;

import com.dylabo.core.common.utils.CommonRequestUtils;
import com.dylabo.dydev.domain.history.entity.AccessHistory;
import com.dylabo.dydev.domain.history.repository.AccessHistoryRepository;
import com.dylabo.dydev.domain.history.service.AccessHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessHistoryServiceImpl implements AccessHistoryService {

    private final AccessHistoryRepository accessHistoryRepository;

    private final ModelMapper modelMapper;

    private List<AccessHistory> getAccessHistoryList(String accessIp) {
        LocalDateTime startDate = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.MAX);

        return accessHistoryRepository.findByAccessIpAndCreateDateTimeBetween(accessIp, startDate, endDate);
    }

    @Override
    @Transactional
    public void setInsertAccessHistory() {
        String accessIp = CommonRequestUtils.getClientIp();

        List<AccessHistory> accessHistoryList = getAccessHistoryList(accessIp);

        if (accessHistoryList.size() < 5) {
            AccessHistory accessHistory = AccessHistory.builder()
                    .accessIp(accessIp)
                    .build();

            accessHistoryRepository.save(accessHistory);
        }
    }
}
