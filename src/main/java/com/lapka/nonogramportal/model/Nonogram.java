package com.lapka.nonogramportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nonogram {
    private Long id;
    private int size;
    private List<Boolean> solution;
    private List<String> art;
}
