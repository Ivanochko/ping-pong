package com.onboard.pongserver.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@RequiredArgsConstructor
public class PongService {

    public static final String PREFIX = "[PongService] ->";

    private static final Logger log = LoggerFactory.getLogger(PongService.class);

    public String pong(long delay) {
        log.info(String.format("%s accepted request, will wait {%d} milliseconds", PREFIX, delay));
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.format("%s delay ended", PREFIX));
        return "PONG";
    }

}
