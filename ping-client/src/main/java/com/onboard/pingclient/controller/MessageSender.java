package com.onboard.pingclient.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageSender {

    public static final String PREFIX = "[MessageSender] ->";

    @Value("${cloud.aws.end-point}")
    private String endpoint;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @GetMapping("/send/ping/{delay}")
    public void send(@PathVariable(value = "delay") Long delay) {

        log.info("{} Sender receive request from user: PING with delay {}", PREFIX, delay);

        String message = "PING:" + delay;
        Message<String> payload = MessageBuilder.withPayload(message)
                .setHeader("sender", "ping-client")
                .build();
        queueMessagingTemplate.send(endpoint, payload);

        log.info("{} Message sent: {}", PREFIX, message);
    }
}
