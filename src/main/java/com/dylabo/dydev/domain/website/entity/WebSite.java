package com.dylabo.dydev.domain.website.entity;

import com.dylabo.core.domain.base.entity.BaseCUEntity;
import com.dylabo.dydev.domain.website.enums.WebSiteTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@SuperBuilder
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(
        name = "web_site"
)
public class WebSite extends BaseCUEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 30)
    private WebSiteTypes webSiteType;

    @NotNull
    @Size(min = 1, max = 30)
    @Column
    private String name;

    @NotNull
    @Size(min = 1, max = 30)
    @Column
    private String description;

    @NotNull
    @Size(min = 1, max = 200)
    @Column
    private String url;

}
