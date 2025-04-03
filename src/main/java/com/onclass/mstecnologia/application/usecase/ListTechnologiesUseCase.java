package com.onclass.mstecnologia.application.usecase;

import com.onclass.mstecnologia.domain.model.Technology;
import reactor.core.publisher.Flux;

public interface ListTechnologiesUseCase {
    Flux<Technology> getAllTechnologies(int page, int size, String sortBy, boolean ascending);

}