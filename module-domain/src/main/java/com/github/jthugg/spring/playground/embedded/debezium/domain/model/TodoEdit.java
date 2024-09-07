package com.github.jthugg.spring.playground.embedded.debezium.domain.model;

public record TodoEdit(
        long id,
        String title,
        String content
) {}
