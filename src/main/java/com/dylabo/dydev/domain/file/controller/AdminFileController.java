package com.dylabo.dydev.domain.file.controller;

import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.file.enums.FileTypes;
import com.dylabo.dydev.domain.file.service.FileService;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_ADMIN + CommonApiUrls.API_PACKAGE_FILE)
public class AdminFileController {

    private final FileService fileService;

    @PostMapping("")
    public ResponseEntity<FileContentResponseDto> doSetUploadFile(
            @RequestPart(value = "fileType") FileTypes fileType,
            @RequestPart(value = "uploadFile") MultipartFile uploadFile) {
        FileContentResponseDto responseDto = fileService.setUploadFiles(fileType, uploadFile);

        if (CommonObjectUtils.nonNull(responseDto)) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
