package com.onboard.pongserver.controller;

import com.onboard.pongserver.service.PongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("ping")
public class PongController {

    private final PongService pongService;

    @GetMapping
    public String ping() {
        return pongService.pong(1000L);
    }

    @GetMapping("{delay}")
    public String ping(@PathVariable("delay") Long delay) {
        return pongService.pong(delay);
    }
}
