package com.dylabo.dydev.domain.system.repository;

import com.dylabo.dydev.domain.system.domain.SystemSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemSettingsRepository extends JpaRepository<SystemSettings, Long> {

    Optional<SystemSettings> findTopByOrderByCreateDateTimeDesc();

}
