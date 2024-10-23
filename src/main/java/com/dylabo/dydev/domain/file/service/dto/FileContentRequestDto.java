package com.dylabo.dydev.domain.file.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileContentRequestDto extends FileContentDto {

    @Getter
    @Builder
    public static class FileUploadDto {
        private MultipartFile uploadFile;

        private int seq;
    }

    @Getter
    public static class FileSeqDto {
        private Long id;

        private int seq;
    }

}
