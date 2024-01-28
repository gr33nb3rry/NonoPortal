package com.lapka.nonogramportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    private Long id;
    private Long userId;
    private Long nonogramId;
}
