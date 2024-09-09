package com.github.jthugg.spring.playground.embedded.debezium.presentation.http.util;

import lombok.Getter;

import java.time.Instant;

@Getter
public class BaseResponse<T> {

    private final Instant timestamp;
    private final T data;

    private BaseResponse(Instant timestamp, T data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public static <T> BaseResponse<T> voidOf() {
        return new BaseResponse<>(Instant.now(), null);
    }

    public static <T> BaseResponse<T> of(T data) {
        return new BaseResponse<>(Instant.now(), data);
    }

}
