package com.dylabo.dydev.domain.file.service;

import com.dylabo.dydev.domain.file.enums.FileTypes;
import com.dylabo.dydev.domain.file.service.dto.FileContentRequestDto;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    FileContentResponseDto getFileContentById(Long fileId);

    List<FileContentResponseDto> getFileContentListById(List<Long> fileIds);

    List<FileContentResponseDto> getFileContentListByRelationId(FileTypes fileType, Long relationId);

    /**
     * 이미지 파일 리소스
     *
     * @param fileId
     * @return
     */
    FileContentResponseDto.FileImageDto getImageFileResource(Long fileId);

    /**
     * 단건 파일 다운로드
     *
     * @param fileId
     * @return
     */
    FileContentResponseDto.FileDownloadDto getDownloadFile(Long fileId);

    /**
     * 단건 파일 업로드
     * no relation
     *
     * @param fileType
     * @param uploadFile
     * @return
     */
    FileContentResponseDto setUploadFiles(FileTypes fileType, MultipartFile uploadFile);

    /**
     * 단건 파일 업로드
     * with relation
     *
     * @param fileType
     * @param uploadFile
     * @param relationId
     * @return
     */
    FileContentResponseDto setUploadFiles(FileTypes fileType, MultipartFile uploadFile, Long relationId);

    /**
     * 다건 파일 업로드
     * no relation
     *
     * @param fileType
     * @param uploadFiles
     * @return
     */
    List<FileContentResponseDto> setUploadFiles(FileTypes fileType, List<MultipartFile> uploadFiles);

    /**
     * 다건 파일 업로드
     * with relation
     *
     * @param fileType
     * @param uploadFiles
     * @param relationId
     * @return
     */
    List<FileContentResponseDto> setUploadFiles(FileTypes fileType, List<MultipartFile> uploadFiles, Long relationId);

    /**
     * 파일 업로드 순번 포함
     *
     * @param fileType
     * @param uploadFiles
     * @return
     */
    List<FileContentResponseDto> setUploadFilesWithSeq(FileTypes fileType, List<FileContentRequestDto.FileUploadDto> uploadFiles, Long relationId);

    /**
     * 파일 순서 업데이트
     *
     * @param fileSeqDtoList
     * @return
     */
    List<FileContentResponseDto> setUpdateFileSeq(List<FileContentRequestDto.FileSeqDto> fileSeqDtoList);

    /**
     * 단건 파일 삭제
     *
     * @param fileId
     */
    void setDeleteFileById(Long fileId);

    /**
     * 다건 파일 삭제
     *
     * @param fileIds
     */
    void setDeleteFilesById(List<Long> fileIds);

    /**
     * 연관 파일 삭제
     *
     * @param fileType
     * @param relationId
     */
    void setDeleteFilesByRelation(FileTypes fileType, Long relationId);

}
