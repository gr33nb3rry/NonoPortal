package com.lapka.nonogramportal.web.controller;

import com.lapka.nonogramportal.business.service.NonogramService;
import com.lapka.nonogramportal.model.Nonogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nonogram")
public class NonogramController {
    private final NonogramService service;
    @Autowired
    public NonogramController(NonogramService service) {
        this.service = service;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Nonogram>> getAllNonograms() {
        return service.getAllNonograms();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping()
    public ResponseEntity<Nonogram> saveNewNonogram(@RequestBody Nonogram nonogram) {
        return service.saveNewNonogram(nonogram);
    }

}
