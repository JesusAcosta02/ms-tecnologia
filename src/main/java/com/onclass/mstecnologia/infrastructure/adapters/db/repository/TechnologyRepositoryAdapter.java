package com.onclass.mstecnologia.infrastructure.adapters.db.repository;

import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import com.onclass.mstecnologia.infrastructure.adapters.db.mappers.TechnologyEntityMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TechnologyRepositoryAdapter implements TechnologyRepository {

    private final ReactiveTechnologyRepository reactiveRepository;

    public TechnologyRepositoryAdapter(ReactiveTechnologyRepository reactiveRepository) {
        this.reactiveRepository = reactiveRepository;
    }

    @Override
    public Mono<Technology> save(Technology technology) {
        return reactiveRepository
                .save(TechnologyEntityMapper.toEntity(technology))
                .map(TechnologyEntityMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return reactiveRepository.existsByName(name);
    }

    @Override
    public Mono<Technology> findById(String id) {
        return reactiveRepository
                .findById(id)
                .map(TechnologyEntityMapper::toDomain);
    }

    @Override
    public Flux<Technology> findAll(int page, int size, String sortBy, boolean ascending) {
        // Spring Data R2DBC no soporta paginación dinámica nativa,
        // así que esto es un placeholder. Se puede hacer con DatabaseClient o manualmente.

        return reactiveRepository.findAll()
                .skip((long) page * size)
                .take(size)
                .map(TechnologyEntityMapper::toDomain);
    }
}