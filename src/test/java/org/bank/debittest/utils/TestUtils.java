package org.bank.debittest.utils;

import org.bank.debittest.domain.model.Debit;

public class TestUtils {

    public static Debit generateTestDebit(){
        return new Debit("01", "Reason01");
    }

}
