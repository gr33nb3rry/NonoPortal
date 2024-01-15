package com.lapka.nonogramportal.business.repository;

import com.lapka.nonogramportal.business.repository.model.NonogramDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonogramRepository extends JpaRepository<NonogramDAO, Long> {
}
