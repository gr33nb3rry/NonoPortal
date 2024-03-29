package com.lapka.nonogramportal.business.repository;

import com.lapka.nonogramportal.business.repository.model.LikeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeDAO, Long> {
    List<LikeDAO> findAllLikeDAOsByNonogramId(Long nonogramId);
}
