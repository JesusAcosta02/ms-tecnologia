package com.onclass.mstecnologia.application.usecase;

import com.onclass.mstecnologia.application.api.TechnologyInfo;
import reactor.core.publisher.Flux;

import java.util.List;

public interface FindTechnologiesByIdUseCase {
    Flux<TechnologyInfo> execute(List<String> ids);
}