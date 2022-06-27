package com.onboard.pingclient.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService {

    public static final String PREFIX = "[SenderService] ->";

    @Value("${aws.local.endpoint}")
    private String endpoint;
    @Value("${cloud.aws.queue.name}")
    private String queueURI;

    private final AmazonSQS amazonSQS;

    public void send(Long delay) {

        String message = String.format("{\"PING\":\"%d\"}", delay);

        SendMessageRequest sendMessageRequest;
        try {
            sendMessageRequest = new SendMessageRequest().withQueueUrl(endpoint + queueURI)
                    .withMessageBody(message)
                    .withMessageGroupId("PING_PONG")
                    .withMessageDeduplicationId(UUID.randomUUID().toString());
            amazonSQS.sendMessage(sendMessageRequest);
            log.info("{} Message has been published in SQS {}", PREFIX, sendMessageRequest);
        } catch (Exception e) {
            log.error("{} Exception occurred while pushing event to sqs : {} and stacktrace ; {}",
                    PREFIX, e.getMessage(), e.getStackTrace());
        }
    }
}
