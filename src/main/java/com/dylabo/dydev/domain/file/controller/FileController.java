package com.dylabo.dydev.domain.file.controller;

import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.dydev.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> doGetDownloadFile() throws UnsupportedEncodingException {
        byte[] fileBytes = fileService.getDownloadFile();

        if (CommonObjectUtils.nonNull(fileBytes)) {
            HttpHeaders headers = new HttpHeaders();
            String encodeFileName = URLEncoder.encode("증명사진.jpg", StandardCharsets.UTF_8.toString());
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodeFileName + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
