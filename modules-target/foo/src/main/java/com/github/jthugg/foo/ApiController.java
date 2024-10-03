package com.github.jthugg.foo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiController {

    @GetMapping("/foo/ping")
    public HttpEntity<?> ping() {
        return ResponseEntity.noContent().build();
    }

}
