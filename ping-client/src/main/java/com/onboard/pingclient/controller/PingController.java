package com.onboard.pingclient.controller;

import com.onboard.pingclient.service.PingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
@RequiredArgsConstructor
public class PingController {

    private final PingService pingService;

    @GetMapping("{delay}")
    public String ping(@PathVariable String delay) {
        return pingService.ping(delay);
    }
}
