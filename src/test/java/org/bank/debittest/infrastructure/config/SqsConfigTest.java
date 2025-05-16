package org.bank.debittest.infrastructure.config;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.junit.jupiter.api.Assertions.*;

class SqsConfigTest {

    @Test
    void shouldCreateSqsAsyncClientBean() {
        SqsConfig config = new SqsConfig();
        SqsAsyncClient client = config.sqsAsyncClient();

        assertNotNull(client);
        assertEquals(Region.US_EAST_1, client.serviceClientConfiguration().region());
    }

}