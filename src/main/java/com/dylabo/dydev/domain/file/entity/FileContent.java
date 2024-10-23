package com.dylabo.dydev.domain.file.entity;

import com.dylabo.core.domain.base.entity.BaseCEntity;
import com.dylabo.dydev.domain.file.enums.FileTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@SuperBuilder
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class FileContent extends BaseCEntity implements Comparable<FileContent> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 30)
    private FileTypes fileType;

    @NotNull
    @Column(length = 200)
    private String originalFileName;

    @NotNull
    @Column(length = 200)
    private String systemFileName;

    @NotNull
    @Column(length = 200)
    private String filePath;

    @NotNull
    @Column(length = 10)
    private String fileExt;

    @NotNull
    @Column(length = 100)
    private String contentType;

    @NotNull
    @Column(columnDefinition = "bigint default 0")
    @Builder.Default
    private Long fileSize = 0L;

    @Column(columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean isPrivate = false;

    @Column(columnDefinition = "integer default 1")
    @Builder.Default
    private Integer seq = 1;

    @Column
    private Long relationId;

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void updateSeq(int seq) {
        this.seq = seq;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    @Override
    public int compareTo(FileContent o) {
        return this.seq.compareTo(o.getSeq());
    }
}
