package org.bank.debittest.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ResponseErrorDTO {
    private String message;
    private String detail;
    private int statusCode;
    private String timeStamp = OffsetDateTime.now().toString();

    public ResponseErrorDTO(String message, String detail, int statusCode) {
        this.message = message;
        this.detail = detail;
        this.statusCode = statusCode;
    }
}
