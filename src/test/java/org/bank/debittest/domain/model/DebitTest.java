package org.bank.debittest.domain.model;

import org.bank.debittest.utils.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebitTest {

    @Test
    void shouldCreateDebitRecord(){
        Debit debit = TestUtils.generateTestDebit();

        assertEquals(debit.id(), "01");
        assertEquals(debit.reason(), "Reason01");
        assertEquals("Debit{id='01', reason='Reason01'}", debit.toString());
    }

}