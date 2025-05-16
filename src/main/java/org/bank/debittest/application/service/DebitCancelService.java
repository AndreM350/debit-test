package org.bank.debittest.application.service;

import lombok.extern.slf4j.Slf4j;
import org.bank.debittest.domain.model.Debit;
import org.bank.debittest.domain.ports.out.SendEventPort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebitCancelService {

    private final SendEventPort sendEventPort;

    public DebitCancelService(SendEventPort sendEventPort) {
        this.sendEventPort = sendEventPort;
    }

    public void cancel(Debit debit){
        log.debug("::: Calling the DebitCancelService with the debit: {}", debit);
        sendEventPort.publishCancelEvent(debit);
    }
}
