package com.lapka.nonogramportal.business.service.impl;

import com.lapka.nonogramportal.Colors;
import com.lapka.nonogramportal.business.mappers.NonogramMapper;
import com.lapka.nonogramportal.business.repository.NonogramRepository;
import com.lapka.nonogramportal.business.repository.model.NonogramDAO;
import com.lapka.nonogramportal.business.service.NonogramService;
import com.lapka.nonogramportal.model.Nonogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NonogramServiceImpl implements NonogramService {
    private final NonogramRepository repository;
    private final NonogramMapper mapper;
    @Autowired
    public NonogramServiceImpl(NonogramRepository repository, NonogramMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<List<Nonogram>> getAllNonograms() {
        List<NonogramDAO> nonogramDAOs = repository.findAll();
        List<Nonogram> nonograms = nonogramDAOs.stream()
                .map(mapper::nonogramDaoToNonogram)
                .toList();
        return new ResponseEntity<>(nonograms, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Nonogram> saveNewNonogram(Nonogram nonogram) {
        if (nonogram.getSize() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (nonogram.getSolution() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (nonogram.getArt() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        NonogramDAO nonogramDao = mapper.nonogramToNonogramDao(nonogram);

        List<String> colors = nonogramDao.getArt();
        List<String> newColors = new ArrayList<>();
        for (String color : colors) {
            newColors.add(String.valueOf(Colors.getId(color)));
        }
        nonogramDao.setArt(newColors);

        NonogramDAO savedNonogram = repository.save(nonogramDao);
        return new ResponseEntity<>(mapper.nonogramDaoToNonogram(savedNonogram), HttpStatus.OK);
    }
}
