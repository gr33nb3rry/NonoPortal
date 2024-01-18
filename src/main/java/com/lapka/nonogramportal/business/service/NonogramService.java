package com.lapka.nonogramportal.business.service;

import com.lapka.nonogramportal.model.Nonogram;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NonogramService {
    public ResponseEntity<List<Nonogram>> getAllNonograms();
    public ResponseEntity<Nonogram> saveNewNonogram(Nonogram nonogram);
}
