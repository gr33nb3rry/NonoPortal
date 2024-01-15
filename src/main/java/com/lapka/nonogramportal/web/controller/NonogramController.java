package com.lapka.nonogramportal.web.controller;

import com.lapka.nonogramportal.business.service.NonogramService;
import org.springframework.beans.factory.annotation.Autowired;

public class NonogramController {
    private final NonogramService service;
    @Autowired
    public NonogramController(NonogramService service) {
        this.service = service;
    }
}
