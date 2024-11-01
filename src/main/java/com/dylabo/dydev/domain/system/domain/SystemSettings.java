package com.dylabo.dydev.domain.system.domain;

import com.dylabo.core.domain.base.entity.BaseCUEntity;
import com.dylabo.dydev.common.enums.SystemLanguageTypes;
import com.dylabo.dydev.common.enums.SystemThemeTypes;
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
@Table(
        name = "system_settings"
)
public class SystemSettings extends BaseCUEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @Column(length = 10)
    private String version;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 10)
    private SystemThemeTypes defaultTheme;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 10)
    private SystemLanguageTypes defaultLang;

    @Column(columnDefinition = "integer default 3")
    @Builder.Default
    private Integer feedDays = 3;

}
