package com.onclass.mstecnologia.infrastructure.adapters.db.repository;

import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import com.onclass.mstecnologia.infrastructure.adapters.db.mappers.TechnologyEntityMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TechnologyRepositoryAdapter implements TechnologyRepository {

    private final ReactiveTechnologyRepository reactiveRepository;
    private final DatabaseClient databaseClient;

    public TechnologyRepositoryAdapter(ReactiveTechnologyRepository reactiveRepository, DatabaseClient databaseClient) {
        this.reactiveRepository = reactiveRepository;
        this.databaseClient = databaseClient;
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
        int offset = page * size;
        String direction = ascending ? "ASC" : "DESC";
        String query = String.format(
                "SELECT id, name, description FROM technologies ORDER BY %s %s LIMIT %d OFFSET %d",
                sortBy, direction, size, offset
        );

        return databaseClient.sql(query)
                .map((row, metadata) -> TechnologyEntityMapper.toDomain(TechnologyEntityMapper.
                        fromRow(row)))
                .all();
    }
}