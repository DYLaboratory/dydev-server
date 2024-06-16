package com.dylabo.dydev.domain.user.entity;

import com.dylabo.core.common.converters.BCryptoConverter;
import com.dylabo.core.domain.base.entity.BaseCUDEntity;
import com.dylabo.dydev.domain.user.enums.UserTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@SuperBuilder
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(
        name = "users"
)
public class User extends BaseCUDEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = 0L;

    @NotNull
    @Size(min = 5, max = 30)
    @Column
    private String userId;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 10)
    private UserTypes userType;

    @NotNull
    @Size(min = 2, max = 30)
    @Column
    private String name;

    @NotNull
    @Size(max = 200)
    @Convert(converter = BCryptoConverter.class)
    @Column
    private String password;

    @NotNull
    @Size(min = 5, max = 200)
    @Column
    private String email;

    @Column(columnDefinition = "integer default 0")
    private Integer loginAttemptCount = 0;

    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime lastLoginDateTime;

    @Builder.Default
    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean accountExpired = false;

    @Builder.Default
    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean credentialExpired = false;

    @Builder.Default
    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean locked = false;

    @Builder.Default
    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.userType.getKey()));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void increaseLoginAttemptCount() {
        this.loginAttemptCount++;
    }

    public void clearLoginAttemptCount() {
        this.loginAttemptCount = 0;
    }

    public void updateLastLoginDateTime() {
        this.lastLoginDateTime = LocalDateTime.now();
    }

    public void successLogin() {
        clearLoginAttemptCount();
        updateLastLoginDateTime();
    }

}
