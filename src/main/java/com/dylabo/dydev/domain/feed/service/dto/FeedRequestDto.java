package com.dylabo.dydev.domain.feed.service.dto;

import com.dylabo.core.domain.base.dto.IdDto;
import com.dylabo.dydev.domain.file.service.dto.FileContentRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedRequestDto extends FeedDto {

    @Getter
    public static class UpdateDto extends FeedRequestDto {
        private List<FileContentRequestDto.FileSeqDto> insertFiles;

        private List<FileContentRequestDto.FileSeqDto> updateFiles;

        private List<IdDto> deleteFiles;
    }

}
