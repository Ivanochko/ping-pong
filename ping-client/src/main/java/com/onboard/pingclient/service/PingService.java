package com.onboard.pingclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PingService {

    public static final String PREFIX = "[PingService] ->";
    private static final String PING_ENDPOINT = "ping/";

    private final RestTemplate restTemplate;

    @Value("${pong.url}")
    private String pongUrl;

    public String ping(String delay) {
        log.info("{} ping request send, waiting '{}' millis...", PREFIX, delay);

        var url = pongUrl + PING_ENDPOINT + delay;
        log.info(url);
        ResponseEntity<String> response = restTemplate.getForEntity(
                url, String.class
        );
        log.info("{} ping response received", PREFIX);
        return response.getBody();
    }


    @PostConstruct
    public void post() {
        checkConnection();
    }

    public void checkConnection() {
        log.info("{} Pong server is on url: {}", PREFIX, pongUrl);

        var url = pongUrl + PING_ENDPOINT + '1';
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(
                    url, String.class
            );
            if (Objects.equals(response.getBody(), "PONG")) {
                log.info("{} Pong server connected!", PREFIX);
            }
        } catch (RestClientException e) {
            log.info("{} Pong server is not connected!", PREFIX);
        }
    }
}
