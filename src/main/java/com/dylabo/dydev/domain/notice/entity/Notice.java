package com.dylabo.dydev.domain.notice.entity;

import com.dylabo.core.domain.base.entity.BaseCUEntity;
import com.dylabo.dydev.domain.notice.enums.NoticeTypes;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
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

    @Column
    @Enumerated(EnumType.STRING)
    private NoticeTypes noticeType;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 40000)
    private String content;

    @Column(columnDefinition = "integer default 0")
    @Builder.Default
    private Integer viewCount = 0;

}
