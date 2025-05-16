package org.bank.debittest.infrastructure.adapter.out.sqs;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.bank.debittest.domain.model.Debit;
import org.bank.debittest.domain.ports.out.SendEventPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;


@Component
@Slf4j
public class SQSEventPublisher implements SendEventPort {

    @Value("${sqs.queue.url}")
    String sqsUrl;

    private final SqsAsyncClient sqsClient;
    private final Gson gson;

    public SQSEventPublisher(SqsAsyncClient sqsClient) {
        this.sqsClient = sqsClient;
        this.gson = new Gson();
    }

    @Override
    public void publishCancelEvent(Debit debit) {

        String body = gson.toJson(debit);

        log.debug("::: Calling the publishCancelEvent with the debit: {}", debit);
        var request = SendMessageRequest.builder()
                .queueUrl(sqsUrl)
                .messageBody(body)
                .messageGroupId("sample-123")
                .build();

        sqsClient.sendMessage(request)
                .exceptionally(ex -> {
                    log.error("::: Error publishing message", ex);
                    return null;
                });
    }
}
