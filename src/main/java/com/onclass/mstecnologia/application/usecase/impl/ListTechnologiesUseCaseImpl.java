package com.onclass.mstecnologia.application.usecase.impl;

import com.onclass.mstecnologia.application.usecase.ListTechnologiesUseCase;
import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class ListTechnologiesUseCaseImpl implements ListTechnologiesUseCase {

    private final TechnologyRepository technologyRepository;

    public ListTechnologiesUseCaseImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public Flux<Technology> getAllTechnologies(int page, int size, String sortBy, boolean ascending) {
        return technologyRepository.findAll(page, size, sortBy, ascending);
    }
}
