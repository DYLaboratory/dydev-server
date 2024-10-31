package com.dylabo.dydev.domain.history.service;

import com.dylabo.dydev.domain.history.service.dto.accesshistory.AccessHistoryRequestDto;

public interface AccessHistoryService {

    void setInsertAccessHistory(AccessHistoryRequestDto accessHistoryRequestDto);

}
