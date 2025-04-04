package com.onclass.mstecnologia.application.usecase.impl;

import com.onclass.mstecnologia.application.api.TechnologyInfo;
import com.onclass.mstecnologia.application.usecase.FindTechnologiesByIdUseCase;
import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindTechnologiesByIdUseCaseImpl implements FindTechnologiesByIdUseCase {

    private final TechnologyRepository technologyRepository;

    @Override
    public Flux<TechnologyInfo> execute(List<String> ids) {
        return technologyRepository.findByIds(ids)
                .map(tech -> TechnologyInfo.builder()
                        .id(tech.getId())
                        .name(tech.getName())
                        .build());
    }
}