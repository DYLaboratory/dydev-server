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
import com.dylabo.dydev.domain.file.service.dto.FileContentDto;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileContentRepository fileContentRepository;

    private final AWSS3Component awsS3Component;

    private final ModelMapper modelMapper;

    private FileContent getEntityFileContentById(Long fileId) {
        return fileContentRepository.findById(fileId)
                .orElseThrow(() -> new ApiException(DydevErrorMessage.FILE_NOT_FOUND));
    }

    @Override
    public FileContentResponseDto getFileContentById(Long fileId) {
        FileContent fileContent = getEntityFileContentById(fileId);

        return modelMapper.map(fileContent, FileContentResponseDto.class);
    }

    @Override
    public FileContentResponseDto.FileDownloadDto getDownloadFile(Long fileId) {
        FileContent fileContent = getEntityFileContentById(fileId);

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
    public FileContentResponseDto setUploadFiles(FileTypes fileType, MultipartFile uploadFile) {
        return setUploadFiles(fileType, uploadFile, null);
    }

    @Override
    @Transactional
    public FileContentResponseDto setUploadFiles(FileTypes fileType, MultipartFile uploadFile, Long relationId) {
        if (CommonObjectUtils.isNull(uploadFile)
                || uploadFile.isEmpty()
                || CommonObjectUtils.isNull(uploadFile.getOriginalFilename())) {
            throw new ApiException(DydevErrorMessage.EMPTY_FILE);
        }

        List<FileContentResponseDto> uploadFiles = setUploadFiles(fileType, List.of(uploadFile), relationId);

        return uploadFiles.isEmpty() ? null : uploadFiles.get(0);
    }

    @Override
    @Transactional
    public List<FileContentResponseDto> setUploadFiles(FileTypes fileType, List<MultipartFile> uploadFiles) {
        return setUploadFiles(fileType, uploadFiles, null);
    }

    @Override
    @Transactional
    public List<FileContentResponseDto> setUploadFiles(FileTypes fileType, List<MultipartFile> uploadFiles, Long relationId) {
        if (uploadFiles.isEmpty()) {
            throw new ApiException(DydevErrorMessage.EMPTY_FILE);
        }

        // file type 없을 경우 임시 파일로 저장
        if (CommonObjectUtils.isNull(fileType)) {
            fileType = FileTypes.TEMP;
            relationId = null; // file type 없이 relation 불가능
        }

        List<FileContentResponseDto> fileContentList = new ArrayList<>();

        try {
            for (MultipartFile uploadFile : uploadFiles) {
                if (CommonObjectUtils.isNull(uploadFile)
                        || uploadFile.isEmpty()
                        || CommonObjectUtils.isNull(uploadFile.getOriginalFilename())) {
                    throw new ApiException(DydevErrorMessage.EMPTY_FILE);
                }

                // file info
                String originalFileName = uploadFile.getOriginalFilename(); // 원본 파일명
                String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // 확장자명 (확장자 없는 파일 등록 불가)
                String systemFileName = UUID.randomUUID().toString().replaceAll("-", "")
                        + CommonConstants.UNDER_BAR
                        + System.currentTimeMillis()
                        + CommonConstants.DOT
                        + fileExt; // 시스템 파일명
                String filePath = generateFilePath(fileType); // 파일 경로

                String contentType = uploadFile.getContentType();
                long fileSize = uploadFile.getSize();

                // set fileContent
                FileContent fileContent = FileContent.builder()
                        .originalFileName(originalFileName)
                        .systemFileName(systemFileName)
                        .filePath(filePath)
                        .fileExt(fileExt)
                        .contentType(contentType)
                        .fileSize(fileSize)
                        .fileType(fileType)
                        .relationId(relationId)
                        .build();

                // save db
                fileContent = fileContentRepository.save(fileContent);

                FileContentResponseDto fileContentResponseDto = modelMapper.map(fileContent, FileContentResponseDto.class);

                // upload to S3
                try {
                    awsS3Component.setUploadS3File(uploadFile, fileContentResponseDto);
                    fileContentList.add(fileContentResponseDto);
                } catch (IOException e) {
                    ErrorLogUtils.printError(log, e);
                }
            }
        } catch (Exception e) {
            // 에러나면 업로드한 파일 삭제
            for (FileContentDto fileContentDto : fileContentList) {
                awsS3Component.setDeleteS3File(fileContentDto);
            }
        }

        return fileContentList;
    }

    @Override
    @Transactional
    public void setDeleteFileById(Long fileId) {
        FileContentResponseDto fileContentResponseDto = getFileContentById(fileId);

        // delete from db
        fileContentRepository.deleteById(fileId);

        // delete file
        awsS3Component.setDeleteS3File(fileContentResponseDto);
    }

    @Override
    @Transactional
    public void setDeleteFilesByRelation(FileTypes fileType, Long relationId) {
        List<FileContent> fileContentList = fileContentRepository.findByFileTypeAndRelationId(fileType, relationId);

        // delete from db
        fileContentRepository.deleteByFileTypeAndRelationId(fileType, relationId);

        // delete files
        awsS3Component.setDeleteS3Files(fileContentList.stream()
                .map(f -> modelMapper.map(f, FileContentDto.class))
                .collect(Collectors.toList())
        );
    }

    /**
     * 파일 경로 생성
     * root/fileType/yyyyMMdd/
     *
     * @param fileType
     * @return
     */
    private String generateFilePath(FileTypes fileType) {
        LocalDate nowDate = LocalDate.now();
        String datePath = String.valueOf(nowDate.getYear())
                + nowDate.getMonthValue()
                + nowDate.getDayOfMonth()
                + CommonConstants.SLASH;

        return fileType.getPath() + datePath;
    }

}
