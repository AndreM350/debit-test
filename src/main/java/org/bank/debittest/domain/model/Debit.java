package org.bank.debittest.domain.model;


import jakarta.validation.constraints.NotBlank;

public record Debit(

        @NotBlank(message = "The value for ID should not be null")
        String id,

        @NotBlank(message = "The value for REASON should not be null")
        String reason
) {

    @Override
    public String toString() {
        return "Debit{" +
                "id='" + id + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
