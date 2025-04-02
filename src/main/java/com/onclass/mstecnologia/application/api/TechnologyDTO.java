package com.onclass.mstecnologia.application.api;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyDTO {
    private String id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    private String description;
}
