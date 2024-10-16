package com.dylabo.dydev.domain.file.service.impl;

import com.dylabo.core.common.constants.CommonConstants;
import com.dylabo.core.common.exception.ApiException;
import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.core.common.utils.ErrorLogUtils;
import com.dylabo.dydev.common.components.AWSS3Component;
import com.dylabo.dydev.common.exception.DydevErrorMessage;
import com.dylabo.dydev.domain.file.entity.FileContent;
import com.dylabo.dydev.domain.file.enums.FileTypes;
import com.dylabo.dydev.domain.file.repository.FileContentRepository;
import com.dylabo.dydev.domain.file.service.FileService;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileContentRepository fileContentRepository;

    private final AWSS3Component awsS3Component;

    private final ModelMapper modelMapper;

    public FileContentResponseDto.FileDownloadDto getDownloadFile(Long fileId) {
        FileContent fileContent = fileContentRepository.findById(fileId)
                .orElseThrow(() -> new ApiException(DydevErrorMessage.FILE_NOT_FOUND));

        String encodeFileName = null;
        try {
            encodeFileName = URLEncoder.encode(fileContent.getOriginalFileName(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        return FileContentResponseDto.FileDownloadDto.builder()
                .fileBytes(awsS3Component.getDownloadS3FileByName(fileContent.getFilePath(), fileContent.getSystemFileName()))
                .fileName(encodeFileName)
                .build();
    }

    @Override
    @Transactional
    public FileContentResponseDto setUploadFile(FileTypes fileTypes, MultipartFile uploadFile) {
        return setUploadFile(fileTypes, uploadFile, false);
    }

    @Override
    @Transactional
    public FileContentResponseDto setUploadFile(FileTypes fileTypes, MultipartFile uploadFile, boolean isPrivate) {
        if (uploadFile.isEmpty() || CommonObjectUtils.isNull(uploadFile.getOriginalFilename())) {
            throw new ApiException(DydevErrorMessage.EMPTY_FILE);
        }

        // file info
        String originalFileName = uploadFile.getOriginalFilename(); // 원본 파일명
        String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 확장자명
        String systemFileName = UUID.randomUUID().toString().replaceAll("-", "")
                + CommonConstants.UNDER_BAR
                + System.currentTimeMillis()
                + CommonConstants.DOT
                + fileExt; // 시스템 파일명
        String contentType = uploadFile.getContentType();
        long fileSize = uploadFile.getSize();

        // set fileContent
        FileContent fileContent = FileContent.builder()
                .originalFileName(originalFileName)
                .systemFileName(systemFileName)
                .filePath(fileTypes.getPath())
                .fileExt(fileExt)
                .contentType(contentType)
                .fileSize(fileSize)
                .isPrivate(isPrivate)
                .build();

        // save db
        fileContent = fileContentRepository.save(fileContent);

        FileContentResponseDto fileContentDto = modelMapper.map(fileContent, FileContentResponseDto.class);

        // upload to S3
        try {
            awsS3Component.setUploadS3File(uploadFile, fileContentDto);
        } catch (IOException e) {
            ErrorLogUtils.printError(log, e);
            return null;
        }

        return fileContentDto;
    }

}
