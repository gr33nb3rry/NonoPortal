package com.lapka.nonogramportal.business.service;

import com.lapka.nonogramportal.model.Like;
import org.springframework.http.ResponseEntity;

public interface LikeService {
    ResponseEntity<Integer> getLikesCountByNonogramId(Long nonogramId);
    ResponseEntity<Like> saveNewLike(Like like);
}
