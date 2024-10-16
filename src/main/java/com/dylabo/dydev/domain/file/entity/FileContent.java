package com.dylabo.dydev.domain.file.entity;

import com.dylabo.core.domain.base.entity.BaseCEntity;
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
public class FileContent extends BaseCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @NotNull
    @Column
    private String originalFileName;

    @NotNull
    @Column
    private String systemFileName;

    @NotNull
    @Column
    private String filePath;

    @NotNull
    @Column
    private String fileExt;

    @NotNull
    @Column
    private String contentType;

    @NotNull
    @Column(columnDefinition = "bigint default 0")
    @Builder.Default
    private Long fileSize = 0L;

    @Column(columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean isPrivate = false;

    @Column(columnDefinition = "integer default 0")
    @Builder.Default
    private Integer relationCount = 0;

    public void increaseRelationCount() {
        ++this.relationCount;
    }

    public void decreaseRelationCount() {
        --this.relationCount;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
