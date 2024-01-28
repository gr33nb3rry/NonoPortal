package com.lapka.nonogramportal.business.service.impl;

import com.lapka.nonogramportal.business.mappers.LikeMapper;
import com.lapka.nonogramportal.business.repository.LikeRepository;
import com.lapka.nonogramportal.business.repository.model.LikeDAO;
import com.lapka.nonogramportal.business.service.LikeService;
import com.lapka.nonogramportal.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repository;
    private final LikeMapper mapper;
    @Autowired
    public LikeServiceImpl(LikeRepository repository, LikeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public ResponseEntity<Integer> getLikesCountByNonogramId(Long nonogramId) {
        List<LikeDAO> foundLikeDAOs = repository.findAllLikeDAOsByNonogramId(nonogramId);
        return new ResponseEntity<>(foundLikeDAOs.size(), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Like> saveNewLike(Like like) {
        if (like.getNonogramId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (like.getUserId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        LikeDAO likeDao = mapper.likeToLikeDao(like);
        LikeDAO savedLike = repository.save(likeDao);
        return new ResponseEntity<>(mapper.likeDaoToLike(savedLike), HttpStatus.OK);
    }
}
