package com.dylabo.dydev.domain.history.controller;

import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.history.service.AccessHistoryService;
import com.dylabo.dydev.domain.history.service.dto.accesshistory.AccessHistoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_ACCESS_HISTORY)
@RequiredArgsConstructor
public class AccessHistoryController {

    private final AccessHistoryService accessHistoryService;

    @PostMapping("")
    public void doSetInsertAccessHistory(@RequestBody AccessHistoryRequestDto accessHistoryRequestDto) {
        accessHistoryService.setInsertAccessHistory(accessHistoryRequestDto);
    }

}
