package com.onclass.mstecnologia.application.usecase;

import com.onclass.mstecnologia.domain.exceptions.DuplicatedTechnologyNameException;
import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import reactor.core.publisher.Mono;

public class SaveTechnologyUseCase {

    private final TechnologyRepository repository;

    public SaveTechnologyUseCase(TechnologyRepository repository) {
        this.repository = repository;
    }

    public Mono<Technology> execute(Technology technology) {
        if (technology.getName() == null || technology.getName().isBlank()) {
            return Mono.error(new IllegalArgumentException("Name is required"));
        }

        if (technology.getName().length() > 50 || technology.getDescription().length() > 90) {
            return Mono.error(new IllegalArgumentException("Name or description length is invalid"));
        }

        return repository.existsByName(technology.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new DuplicatedTechnologyNameException(technology.getName()));
                    }
                    return repository.save(technology);
                });
    }
}