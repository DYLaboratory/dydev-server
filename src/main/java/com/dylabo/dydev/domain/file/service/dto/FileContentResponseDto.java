package com.dylabo.dydev.domain.file.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.InputStreamResource;

@Getter
public class FileContentResponseDto extends FileContentDto {

    private Long id;

    @Getter
    @Builder
    public static class FileImageDto {
        private InputStreamResource isr;

        private String contentType;
    }

    @Getter
    @Builder
    public static class FileDownloadDto {
        private byte[] fileBytes;

        private String fileName;
    }

    @Getter
    public static class FileInfoDto {
        private Long id;

        private String fileExt;

        private int seq;
    }

}
