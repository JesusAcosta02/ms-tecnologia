package com.onclass.mstecnologia.infrastructure.adapters.db.mappers;

import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.infrastructure.adapters.db.entity.TechnologyEntity;

public class TechnologyEntityMapper {

    public static Technology toDomain(TechnologyEntity entity) {
        return new Technology(entity.getId(), entity.getName(), entity.getDescription());
    }

    public static TechnologyEntity toEntity(Technology technology) {
        return TechnologyEntity.builder()
                .id(technology.getId())
                .name(technology.getName())
                .description(technology.getDescription())
                .build();
    }
}