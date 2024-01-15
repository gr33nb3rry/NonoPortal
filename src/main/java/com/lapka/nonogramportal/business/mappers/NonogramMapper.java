package com.lapka.nonogramportal.business.mappers;

import com.lapka.nonogramportal.business.repository.model.NonogramDAO;
import com.lapka.nonogramportal.model.Nonogram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NonogramMapper {
    @Mapping(source = "id", target = "id")
    Nonogram nonogramDaoToNonogram(NonogramDAO nonogramDao);
    @Mapping(source = "id", target = "id")
    NonogramDAO nonogramToNonogramDao(Nonogram nonogram);
}
