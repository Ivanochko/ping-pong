package com.onboard.pongserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PongService {

    public static final String PREFIX = "[PongService] ->";

    public String pong(long delay) {
        log.info("{} accepted request, will wait '{}' milliseconds", PREFIX, delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} delay ended", PREFIX);
        return "PONG";
    }

}
