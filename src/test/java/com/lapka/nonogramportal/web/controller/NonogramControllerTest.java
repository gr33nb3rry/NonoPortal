package com.lapka.nonogramportal.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapka.nonogramportal.business.service.impl.NonogramServiceImpl;
import com.lapka.nonogramportal.model.Nonogram;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NonogramControllerTest {
    String url = "/api/v1/nonogram";
    private final MockMvc mvc;
    @Autowired
    public NonogramControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }
    @MockBean
    private NonogramServiceImpl service;
    @Test
    @WithMockUser
    void getAllNonograms() throws Exception {
        List<Nonogram> listOfNonograms = new ArrayList<>();
        listOfNonograms.add(new Nonogram());
        listOfNonograms.add(new Nonogram());
        when(service.getAllNonograms()).thenReturn(new ResponseEntity<>(listOfNonograms, HttpStatus.OK));
        mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk());
        verify(service, Mockito.times(1)).getAllNonograms();
    }

    @Test
    @WithMockUser
    void saveNewNonogram() throws Exception {
        List<Boolean> solution = new ArrayList<>();
        solution.add(true);
        solution.add(false);
        solution.add(true);
        solution.add(false);
        List<String> art = new ArrayList<>();
        art.add("2");
        art.add("3");
        art.add("4");
        art.add("1");
        Nonogram nonogram = new Nonogram(1L, 2, solution, art);
        ObjectMapper objectMapper = new ObjectMapper();
        String nonogramJSON = objectMapper.writeValueAsString(nonogram);
        when(service.saveNewNonogram(nonogram)).thenReturn(new ResponseEntity<>(nonogram, HttpStatus.OK));
        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(nonogramJSON))
                .andExpect(status().isOk());
        verify(service, Mockito.times(1)).saveNewNonogram(nonogram);
    }
}