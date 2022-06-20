package com.onboard.pongserver.controller;

import com.onboard.pongserver.service.PongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageReceiver {

    public static final String PREFIX = "[MessageReceiver] ->";

    private final PongService pongService;

    @SqsListener("PingPongQueue")
    public void receive(String message,
                        @Header("sender") String sender) {

        log.info("{} Message received '{}' from {}", PREFIX, message, sender);
        String[] splitted = message.split(":");
        if (splitted.length < 2) {
            log.error("Message is incorrect. Correct template is: PING:delay");
        }
        log.info("{} {}", PREFIX, splitted[0]);
        long delay = Long.parseLong(splitted[1]);
        pongService.pong(delay);
    }

}
