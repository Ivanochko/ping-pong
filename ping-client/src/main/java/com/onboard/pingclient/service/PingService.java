package com.onboard.pingclient.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PingService {

    public static final String PREFIX = "[PingService] ->";
    private static final String PING_ENDPOINT = "ping/";

    private static final Logger log = LoggerFactory.getLogger(PingService.class);

    private final RestTemplate restTemplate;

    @Value("${pong.url}")
    private String pongUrl;

    public String ping(String delay) {
        log.info(String.format("%s ping request send, waiting {%s} millis...", PREFIX, delay));

        var url = pongUrl + PING_ENDPOINT + delay;
        log.info(url);
        ResponseEntity<String> response = restTemplate.getForEntity(
                url, String.class
        );
        log.info(String.format("%s ping response received", PREFIX));
        return response.getBody();
    }


    @PostConstruct
    public void post() {
        checkConnection();
    }

    public void checkConnection() {
        log.info(String.format("%s Pong server is on url: %s", PREFIX, pongUrl));

        var url = pongUrl + PING_ENDPOINT + '1';
        ResponseEntity<String> response = restTemplate.getForEntity(
                url, String.class
        );
        if (Objects.equals(response.getBody(), "PONG")) {
            log.info(String.format("%s Pong server connected!", PREFIX));
        }
    }
}
