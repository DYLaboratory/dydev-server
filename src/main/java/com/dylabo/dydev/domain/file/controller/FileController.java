package com.dylabo.dydev.domain.file.controller;

import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.dydev.common.constants.CommonApiUrls;
import com.dylabo.dydev.domain.file.service.FileService;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
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

    @GetMapping("/image/{id}")
    public ResponseEntity<InputStreamResource> doGetImageFile(@PathVariable("id") Long fileId) {
        FileContentResponseDto.FileImageDto imageDto = fileService.getImageFileResource(fileId);

        if (CommonObjectUtils.isNull(imageDto) || CommonObjectUtils.isNull(imageDto.getIsr())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, imageDto.getContentType());

            return new ResponseEntity<>(imageDto.getIsr(), headers, HttpStatus.OK);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> doGetDownloadFile(@PathVariable("id") Long fileId) {
        FileContentResponseDto.FileDownloadDto fileDto = fileService.getDownloadFile(fileId);

        if (CommonObjectUtils.isNull(fileDto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getFileName() + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return new ResponseEntity<>(fileDto.getFileBytes(), headers, HttpStatus.OK);
        }
    }
}
