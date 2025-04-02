package com.onclass.mstecnologia.application.api;

import lombok.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyDTO {
    private String id;
    private String name;
    private String description;
}
