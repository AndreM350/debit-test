package org.bank.debittest.infrastructure.adapter.out.sqs;

import org.bank.debittest.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SQSEventPublisherTest {

    @Mock
    private SqsAsyncClient sqsClient;

    @InjectMocks
    private SQSEventPublisher publisher;

    @Captor
    private ArgumentCaptor<SendMessageRequest> requestCaptor;

    @Test
    void shouldPublishCancelEventToSqs() {
        var debit = TestUtils.generateTestDebit();
        when(sqsClient.sendMessage(any(SendMessageRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        publisher.publishCancelEvent(debit);

        verify(sqsClient).sendMessage(requestCaptor.capture());
        SendMessageRequest request = requestCaptor.getValue();
        assertTrue(request.messageBody().contains("Reason01"));
    }

    @Test
    void shouldHandleExceptionWhenPublishing() {
        var debit = TestUtils.generateTestDebit();
        when(sqsClient.sendMessage(any(SendMessageRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("fail")));

        assertDoesNotThrow(() -> publisher.publishCancelEvent(debit));
    }

}