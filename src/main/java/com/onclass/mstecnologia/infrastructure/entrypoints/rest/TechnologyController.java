package com.onclass.mstecnologia.infrastructure.entrypoints.rest;

import com.onclass.mstecnologia.application.api.TechnologyDTO;
import com.onclass.mstecnologia.application.usecase.impl.ListTechnologiesUseCaseImpl;
import com.onclass.mstecnologia.application.usecase.impl.SaveTechnologyUseCaseImpl;
import com.onclass.mstecnologia.infrastructure.adapters.db.mappers.TechnologyMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final SaveTechnologyUseCaseImpl saveTechnologyUseCaseImpl;
    private final ListTechnologiesUseCaseImpl listTechnologiesUseCase;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TechnologyDTO> createTechnology(@Valid @RequestBody TechnologyDTO dto) {
        return saveTechnologyUseCaseImpl.execute(TechnologyMapper.toDomain(dto))
                .map(TechnologyMapper::toDTO);
    }

    @GetMapping
    public Flux<TechnologyDTO> getAllTechnologies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {

        return listTechnologiesUseCase.getAllTechnologies(page, size, sortBy, ascending)
                .map(TechnologyMapper::toDTO);
    }

}