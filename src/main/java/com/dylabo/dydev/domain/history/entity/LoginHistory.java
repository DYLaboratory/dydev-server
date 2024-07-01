package com.dylabo.dydev.domain.history.entity;

import com.dylabo.core.domain.base.entity.BaseCEntity;
import com.dylabo.dydev.domain.history.enums.LoginHistoryMessage;
import com.dylabo.dydev.domain.user.entity.User;
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
        name = "login_history"
)
public class LoginHistory extends BaseCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @ManyToOne
    @JoinColumn(name = "access_user_id", foreignKey = @ForeignKey(name = "FK_USER_LOGIN_HISTORY"))
    private User user;

    @NotNull
    @Column
    private String accessIp;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column
    private LoginHistoryMessage history;

}
