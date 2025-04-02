package com.onclass.mstecnologia.domain.repository;

import com.onclass.mstecnologia.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyRepository {

    Mono<Technology> save(Technology technology);

    Mono<Boolean> existsByName(String name);

    Mono<Technology> findById(String id);

    Flux<Technology> findAll(int page, int size, String sortBy, boolean ascending);
}