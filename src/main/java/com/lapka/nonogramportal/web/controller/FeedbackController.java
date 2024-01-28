package com.lapka.nonogramportal.web.controller;

import com.lapka.nonogramportal.business.service.LikeService;
import com.lapka.nonogramportal.business.service.NonogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private final LikeService likeService;
    @Autowired
    public FeedbackController(LikeService likeService) {
        this.likeService = likeService;
    }
}
