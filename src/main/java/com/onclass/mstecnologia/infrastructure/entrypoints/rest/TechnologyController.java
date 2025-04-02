package com.onclass.mstecnologia.infrastructure.entrypoints.rest;

import com.onclass.mstecnologia.application.api.TechnologyDTO;
import com.onclass.mstecnologia.application.usecase.SaveTechnologyUseCase;
import com.onclass.mstecnologia.infrastructure.adapters.db.mappers.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final SaveTechnologyUseCase saveTechnologyUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TechnologyDTO> createTechnology(@RequestBody TechnologyDTO dto) {
        return saveTechnologyUseCase.execute(TechnologyMapper.toDomain(dto))
                .map(TechnologyMapper::toDTO);
    }
}