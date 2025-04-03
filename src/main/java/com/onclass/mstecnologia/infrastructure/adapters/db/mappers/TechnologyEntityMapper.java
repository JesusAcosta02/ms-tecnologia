package com.onclass.mstecnologia.infrastructure.adapters.db.mappers;

import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.infrastructure.adapters.db.entity.TechnologyEntity;
import io.r2dbc.spi.Row;

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

    public static TechnologyEntity fromRow(Row row) {
        return new TechnologyEntity(
                row.get("id", String.class),
                row.get("name", String.class),
                row.get("description", String.class)
        );
    }
}