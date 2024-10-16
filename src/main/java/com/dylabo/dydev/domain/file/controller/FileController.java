package com.dylabo.dydev.domain.file.controller;

import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.file.service.FileService;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CommonApiUrls.API_PACKAGE_PREFIX_COMMON + CommonApiUrls.API_PACKAGE_FILE)
public class FileController {

    private final FileService fileService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> doGetDownloadFile(@PathVariable("id") Long fileId) {
        FileContentResponseDto.FileDownloadDto fileDto = fileService.getDownloadFile(fileId);

        if (CommonObjectUtils.isNull(fileDto)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if (CommonObjectUtils.nonNull(fileDto.getFileBytes())) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getFileName() + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return new ResponseEntity<>(fileDto.getFileBytes(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
