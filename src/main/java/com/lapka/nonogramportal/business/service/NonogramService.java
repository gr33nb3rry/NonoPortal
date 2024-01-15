package com.lapka.nonogramportal.business.service;

import com.lapka.nonogramportal.model.Nonogram;

import java.util.List;

public interface NonogramService {
    public List<Nonogram> getAllNonograms();
    public Nonogram saveNewNonogram(Nonogram nonogram);
}
