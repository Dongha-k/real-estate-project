package com.project.real_estate_1.repository;

import com.project.real_estate_1.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRep extends JpaRepository<License, Long> {
    
}
