package com.onclass.mstecnologia.infrastructure.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyEntity {

    @Id
    private String id;

    private String name;

    private String description;
}