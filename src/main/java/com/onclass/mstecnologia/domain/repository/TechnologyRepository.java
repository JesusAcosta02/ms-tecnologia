package com.onclass.mstecnologia.domain.repository;

import com.onclass.mstecnologia.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyRepository {

    Mono<Technology> save(Technology technology);

    Mono<Boolean> existsByName(String name);

    Flux<Technology> findByIds(List<String> capacityId);


    Flux<Technology> findAll(int page, int size, String sortBy, boolean ascending);
}