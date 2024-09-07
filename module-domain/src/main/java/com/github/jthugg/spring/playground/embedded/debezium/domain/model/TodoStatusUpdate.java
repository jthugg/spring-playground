package com.github.jthugg.spring.playground.embedded.debezium.domain.model;

public record TodoStatusUpdate(
        long id,
        TodoStatus status
) {}
