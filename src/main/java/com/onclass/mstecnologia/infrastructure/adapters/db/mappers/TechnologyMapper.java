package com.onclass.mstecnologia.infrastructure.adapters.db.mappers;


import com.onclass.mstecnologia.application.api.TechnologyDTO;
import com.onclass.mstecnologia.domain.model.Technology;

public class TechnologyMapper {

    public static Technology toDomain(TechnologyDTO dto) {
        return new Technology(dto.getId(), dto.getName(), dto.getDescription());
    }

    public static TechnologyDTO toDTO(Technology technology) {
        return TechnologyDTO.builder()
                .id(technology.getId())
                .name(technology.getName())
                .description(technology.getDescription())
                .build();
    }
}
