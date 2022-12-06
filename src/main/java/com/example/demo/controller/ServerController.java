package com.example.demo.controller;

import com.example.demo.dto.Foo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/app")
public class ServerController {

    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Foo> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(s ->new Foo(1, "foo"));
    }
}
