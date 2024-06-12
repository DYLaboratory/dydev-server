package com.dylabo.dydev.domain.history.repository;

import com.dylabo.dydev.domain.history.entity.SignInHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignInHistoryRepository extends JpaRepository<SignInHistory, Long> {
}
