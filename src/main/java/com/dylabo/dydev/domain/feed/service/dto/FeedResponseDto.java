package com.dylabo.dydev.domain.feed.service.dto;

import com.dylabo.core.domain.base.dto.IdDto;
import com.dylabo.dydev.domain.file.service.dto.FileContentResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedResponseDto extends FeedDto {

    private Long id;

    private List<FileContentResponseDto.FileInfoDto> fileList;

    public void setFileList(List<FileContentResponseDto.FileInfoDto> fileList) {
        this.fileList = fileList;
    }
}
