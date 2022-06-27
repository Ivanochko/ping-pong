package com.onboard.pongserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiverService {

    public static final String PREFIX = "[ReceiverService] ->";

    private final PongService pongService;

    @SqsListener("${cloud.aws.queue.name}")
    public void receiveMessage(Map<String, Object> message) {
        log.info("{} SQS Message Received : {}", PREFIX, message);
        String delayString = (String) message.getOrDefault("PING", 0L);
        Long delay = Long.valueOf(delayString);

        log.info("{} Delay is {}", PREFIX, delay);

        log.info(pongService.pong(delay));
    }

}
