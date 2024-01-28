package com.lapka.nonogramportal.web.controller;

import com.lapka.nonogramportal.business.service.LikeService;
import com.lapka.nonogramportal.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private final LikeService likeService;
    @Autowired
    public FeedbackController(LikeService likeService) {
        this.likeService = likeService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "byId")
    public ResponseEntity<Integer> getLikesCountByNonogramId(@RequestParam Long nonogramId) {
        return likeService.getLikesCountByNonogramId(nonogramId);
    }
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping()
    public ResponseEntity<Like> saveNewLike(@RequestBody Like like) {
        return likeService.saveNewLike(like);
    }
}
