package org.bank.debittest.infrastructure.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseErrorDTOTest {

    @Test
    void shouldBuildDTO() {
        String message = "Error message";
        String detail = "Detail about the error";
        int statusCode = 400;

        ResponseErrorDTO dto = new ResponseErrorDTO(message, detail, statusCode);

        assertEquals(message, dto.getMessage());
        assertEquals(detail, dto.getDetail());
        assertEquals(statusCode, dto.getStatusCode());
    }
}