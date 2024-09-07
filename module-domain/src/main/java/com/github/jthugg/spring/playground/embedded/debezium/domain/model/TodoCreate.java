package com.github.jthugg.spring.playground.embedded.debezium.domain.model;

import jakarta.validation.constraints.NotBlank;

public record TodoCreate(
        @NotBlank String title,
        String content
) {}
