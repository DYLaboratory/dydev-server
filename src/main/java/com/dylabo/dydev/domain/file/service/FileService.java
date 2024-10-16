package com.dylabo.dydev.domain.file.service;

import com.dylabo.dydev.domain.file.enums.FileTypes;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileContentResponseDto.FileDownloadDto getDownloadFile(Long fileId);

    FileContentResponseDto setUploadFile(FileTypes fileTypes, MultipartFile uploadFile);

    FileContentResponseDto setUploadFile(FileTypes fileTypes, MultipartFile uploadFile, boolean isPrivate);

}
