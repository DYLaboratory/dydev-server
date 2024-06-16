package com.dylabo.dydev.domain.website.repository;

import com.dylabo.dydev.domain.website.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebSiteRepository extends JpaRepository<WebSite, Long> {
    @Query(value = "select ws from WebSite ws "
            + "where 1=1 "
            + "order by ws.updateDateTime desc")
    List<WebSite> findAllBySort();

    void deleteByIdIn(List<Long> idList);
}
