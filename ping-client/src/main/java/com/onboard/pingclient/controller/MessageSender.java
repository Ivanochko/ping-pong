package com.onboard.pingclient.controller;

import com.onboard.pingclient.service.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageSender {

    public static final String PREFIX = "[MessageSender] ->";

    private final SenderService senderService;

    @GetMapping("/send/ping/{delay}")
    public ResponseEntity<String> send(@PathVariable(value = "delay") Long delay) {
        log.info("{} Sender receive request from user: PING with delay {}", PREFIX, delay);
        senderService.send(delay);
        return ResponseEntity.ok().body("PONG");
    }

}
