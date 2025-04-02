package com.onclass.mstecnologia.infrastructure.config;

import com.onclass.mstecnologia.application.usecase.SaveTechnologyUseCase;
import com.onclass.mstecnologia.domain.repository.TechnologyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SaveTechnologyUseCase saveTechnologyUseCase(TechnologyRepository repository) {
        return new SaveTechnologyUseCase(repository);
    }
}