package com.onclass.mstecnologia.application.usecase;

import com.onclass.mstecnologia.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface SaveTechnologyUseCase {
    Mono<Technology> execute(Technology technology);
}