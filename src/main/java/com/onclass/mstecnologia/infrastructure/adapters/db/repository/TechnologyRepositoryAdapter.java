package com.onclass.mstecnologia.infrastructure.adapters.db.repository;

import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import com.onclass.mstecnologia.infrastructure.adapters.db.mappers.TechnologyEntityMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Repository
public class TechnologyRepositoryAdapter implements TechnologyRepository {

    private final ReactiveTechnologyRepository reactiveRepository;
    private final DatabaseClient databaseClient;

    @Override
    public Flux<Technology> findByIds(List<String> ids) {
        if (ids.isEmpty()) return Flux.empty();
        String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = "SELECT id, name FROM technologies WHERE id IN (" + placeholders + ")";
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(sql);
        for (int i = 0; i < ids.size(); i++) {
            spec = spec.bind(i, ids.get(i));
        }
        return spec.map((row, metadata) ->
                        TechnologyEntityMapper.toDomain(TechnologyEntityMapper.fromRowWithoutDescription(row))
                )
                .all();
    }


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