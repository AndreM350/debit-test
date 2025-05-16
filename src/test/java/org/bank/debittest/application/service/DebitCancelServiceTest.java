package org.bank.debittest.application.service;

import org.bank.debittest.domain.model.Debit;
import org.bank.debittest.domain.ports.out.SendEventPort;
import org.bank.debittest.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DebitCancelServiceTest {

    @Mock
    private SendEventPort sendEventPort;

    @InjectMocks
    private DebitCancelService service;

    @Test
    void shouldCallPublishCancelEvent() {
        var debit = TestUtils.generateTestDebit();
        service.cancel(debit);
        verify(sendEventPort).publishCancelEvent(debit);
    }
}