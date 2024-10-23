package com.dylabo.dydev.domain.file.service.dto;

import com.dylabo.core.domain.base.dto.BaseCDto;
import lombok.Getter;

@Getter
public class FileContentDto extends BaseCDto implements Comparable<FileContentDto> {

    private String originalFileName;

    private String systemFileName;

    private String filePath;

    private String fileExt;

    private String contentType;

    private Long fileSize;

    private Boolean isPrivate;

    private Integer seq;

    @Override
    public int compareTo(FileContentDto o) {
        return this.seq.compareTo(o.getSeq());
    }

}
