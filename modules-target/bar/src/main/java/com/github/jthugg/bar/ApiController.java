package com.github.jthugg.bar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiController {

    @GetMapping("/bar/ping")
    public HttpEntity<?> ping() {
        return ResponseEntity.noContent().build();
    }

}
