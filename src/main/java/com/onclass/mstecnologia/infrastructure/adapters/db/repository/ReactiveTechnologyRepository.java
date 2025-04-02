package com.onclass.mstecnologia.infrastructure.adapters.db.repository;

import com.onclass.mstecnologia.infrastructure.adapters.db.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReactiveTechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, String> {
    Mono<Boolean> existsByName(String name);
}