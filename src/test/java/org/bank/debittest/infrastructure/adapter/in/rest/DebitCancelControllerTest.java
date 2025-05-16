package org.bank.debittest.infrastructure.adapter.in.rest;

import org.bank.debittest.application.service.DebitCancelService;
import org.bank.debittest.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DebitCancelControllerTest {

    @Mock
    private DebitCancelService debitCancelService;

    @InjectMocks
    private DebitCancelController controller;

    @Test
    void shouldReturn201WhenCancelIsCalled() {
        var debit = TestUtils.generateTestDebit();
        ResponseEntity<String> response = controller.cancel(debit);

        verify(debitCancelService).cancel(debit);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cancel request received", response.getBody());
    }
}