package com.phearun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phearun.model.DynamicWebsite;

@Repository
public interface DynamicWebsiteRepository extends JpaRepository<DynamicWebsite, Integer>{

}
