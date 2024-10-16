package com.dylabo.dydev.domain.file.repository;

import com.dylabo.dydev.domain.file.entity.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileContentRepository extends JpaRepository<FileContent, Long> {
}
