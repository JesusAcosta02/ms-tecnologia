package com.onclass.mstecnologia.infrastructure.entrypoints.rest;

import com.onclass.mstecnologia.application.api.TechnologyDTO;
import com.onclass.mstecnologia.application.usecase.SaveTechnologyUseCase;
import com.onclass.mstecnologia.domain.model.Technology;
import com.onclass.mstecnologia.infrastructure.entrypoints.rest.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = TechnologyController.class)
@Import({TechnologyControllerTest.MockedBeansConfig.class, GlobalExceptionHandler.class})
class TechnologyControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SaveTechnologyUseCase saveTechnologyUseCase;

    @TestConfiguration
    static class MockedBeansConfig {
        @Bean
        public SaveTechnologyUseCase saveTechnologyUseCase() {
            return Mockito.mock(SaveTechnologyUseCase.class);
        }
    }

    @Test
    void shouldCreateTechnologySuccessfully() {
        var dto = new TechnologyDTO("4", "Java", "Lenguaje backend");
        var expected = new Technology("1", "Java", "Lenguaje backend");

        when(saveTechnologyUseCase.execute(any())).thenReturn(Mono.just(expected));

        webTestClient.post()
                .uri("/technologies")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("Java")
                .jsonPath("$.description").isEqualTo("Lenguaje backend");
    }
}
