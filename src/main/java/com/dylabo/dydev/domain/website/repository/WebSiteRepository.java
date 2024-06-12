package com.dylabo.dydev.domain.website.repository;

import com.dylabo.dydev.domain.website.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebSiteRepository extends JpaRepository<WebSite, Long> {
}
