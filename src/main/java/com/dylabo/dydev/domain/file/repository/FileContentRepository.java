package com.dylabo.dydev.domain.file.repository;

import com.dylabo.dydev.domain.file.entity.FileContent;
import com.dylabo.dydev.domain.file.enums.FileTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileContentRepository extends JpaRepository<FileContent, Long> {

    List<FileContent> findByFileTypeAndRelationIdOrderBySeqAsc(FileTypes fileType, Long relationId);

    void deleteByFileTypeAndRelationId(FileTypes fileType, Long relationId);

}
