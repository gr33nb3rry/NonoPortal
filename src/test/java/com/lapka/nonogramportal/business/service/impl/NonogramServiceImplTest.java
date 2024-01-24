package com.lapka.nonogramportal.business.service.impl;

import com.lapka.nonogramportal.business.mappers.NonogramMapper;
import com.lapka.nonogramportal.business.repository.NonogramRepository;
import com.lapka.nonogramportal.business.repository.model.NonogramDAO;
import com.lapka.nonogramportal.model.Nonogram;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class NonogramServiceImplTest {
    @Mock
    private NonogramMapper mapper;
    @Mock
    private NonogramRepository repository;
    @InjectMocks
    private NonogramServiceImpl service;

    private Nonogram nonogram;
    private NonogramDAO nonogramDao;
    @BeforeEach
    public void init() {
        List<Boolean> solution = new ArrayList<>();
        solution.add(true);
        solution.add(false);
        solution.add(true);
        solution.add(false);
        List<String> art = new ArrayList<>();
        art.add("#0000FF");
        art.add("#FF4500");
        art.add("#FFA07A");
        art.add("#000000");
        nonogram = new Nonogram(1L, 2, solution, art);
        nonogramDao = new NonogramDAO(1L, 2, solution, art);
    }

    @Test
    void getAllNonograms() {
        List<NonogramDAO> listOfNonogramsDao = new ArrayList<>();
        listOfNonogramsDao.add(nonogramDao);
        listOfNonogramsDao.add(nonogramDao);
        when(repository.findAll()).thenReturn(listOfNonogramsDao);
        when(mapper.nonogramDaoToNonogram(nonogramDao)).thenReturn(nonogram);
        ResponseEntity<List<Nonogram>> result = service.getAllNonograms();
        verify(repository, Mockito.times(1)).findAll();
        verifyNoMoreInteractions(repository);
        List<Nonogram> expected = new ArrayList<>();
        expected.add(nonogram);
        expected.add(nonogram);
        Assertions.assertThat(result).isEqualTo(new ResponseEntity<>(expected, HttpStatus.OK));
    }

    @Test
    void successSaveNewNonogramTest() {
        when(mapper.nonogramToNonogramDao(nonogram)).thenReturn(nonogramDao);
        when(mapper.nonogramDaoToNonogram(nonogramDao)).thenReturn(nonogram);
        when(repository.save(nonogramDao)).thenReturn(nonogramDao);
        ResponseEntity<Nonogram> result = service.saveNewNonogram(nonogram);
        verify(repository, Mockito.times(1)).save(nonogramDao);
        verifyNoMoreInteractions(repository);
        Assertions.assertThat(result).isEqualTo(new ResponseEntity<>(nonogram, HttpStatus.OK));
    }
    @Test
    void failedSaveNewNonogramDueToNullSizeTest() {
        nonogram.setSize(null);
        ResponseEntity<Nonogram> result = service.saveNewNonogram(nonogram);
        Assertions.assertThat(result).isEqualTo(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
    @Test
    void failedSaveNewNonogramDueToNullArtTest() {
        nonogram.setArt(null);
        ResponseEntity<Nonogram> result = service.saveNewNonogram(nonogram);
        Assertions.assertThat(result).isEqualTo(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
    @Test
    void failedSaveNewNonogramDueToNullSolutionTest() {
        nonogram.setSolution(null);
        ResponseEntity<Nonogram> result = service.saveNewNonogram(nonogram);
        Assertions.assertThat(result).isEqualTo(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}