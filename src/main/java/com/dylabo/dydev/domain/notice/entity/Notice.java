package com.dylabo.dydev.domain.notice.entity;

import com.dylabo.core.domain.base.entity.BaseCUEntity;
import com.dylabo.dydev.domain.notice.enums.NoticeTypes;
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
public class Notice extends BaseCUEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 30)
    private NoticeTypes noticeType;

    @NotNull
    @Column(length = 200)
    private String title;

    @NotNull
    @Column(length = 40000)
    private String content;

    @Column(columnDefinition = "integer default 0")
    @Builder.Default
    private Integer viewCount = 0;

}
