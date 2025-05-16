package org.bank.debittest.domain.ports.out;

import org.bank.debittest.domain.model.Debit;

public interface SendEventPort {
    void publishCancelEvent(Debit debit);
}
