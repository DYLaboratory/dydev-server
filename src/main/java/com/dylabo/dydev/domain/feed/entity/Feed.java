package com.dylabo.dydev.domain.feed.entity;

import com.dylabo.core.domain.base.entity.BaseCEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(
        name = "feed"
)
public class Feed extends BaseCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @NotNull
    @Column(length = 50)
    private String title;

    @NotNull
    @Column(length = 1000)
    private String content;

    @Column(length = 30)
    private String place;

    @Column(length = 200)
    private String link;

    @Column(length = 200)
    private String tags;

    @Column(columnDefinition = "integer default 0")
    @Builder.Default
    private Integer viewCount = 0;

}
