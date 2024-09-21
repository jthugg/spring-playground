package com.github.jthugg.spring.playground.embedded.debezium.presentation.debezium.controller;

import io.debezium.config.Configuration;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class CDCController {

    private final Executor debeziumExecutor;
    private final DebeziumEngine<ChangeEvent<String, String>> debeziumEngine;

    public CDCController(
            Executor debeziumExecutor,
            Configuration configuration
    ) {
        this.debeziumExecutor = debeziumExecutor;
        this.debeziumEngine = DebeziumEngine.create(Json.class)
                .using(configuration.asProperties())
                .notifying(this::handleCDCEvent)
                .build();
    }

    @PostConstruct
    public void startDebeziumEngine() {
        this.debeziumExecutor.execute(debeziumEngine);
    }

    @PreDestroy
    public void stopDebeziumEngine() throws IOException {
        if (debeziumEngine != null) {
            this.debeziumEngine.close();
        }
    }

    public void handleCDCEvent(ChangeEvent<String, String> event) {
        log.info("key: {}\n\nvalue: {}", event.key(), event.value());
    }

}
