package org.bank.debittest.infrastructure.adapter.in.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bank.debittest.application.service.DebitCancelService;
import org.bank.debittest.domain.model.Debit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debit-cancel")
@Tag(name = "Debit Cancel", description = "Sends the request to cancel a debit")
public class DebitCancelController {

    private final DebitCancelService debitCancelService;

    public DebitCancelController(DebitCancelService debitCancelService) {
        this.debitCancelService = debitCancelService;
    }

    @Operation(summary = "Cancels the debit", description = "Publish the cancel event into the SQS")
    @ApiResponse(responseCode = "201", description = "Cancel request received")
    @PostMapping
    public ResponseEntity<String> cancel(@Valid @RequestBody Debit debit) {
        debitCancelService.cancel(debit);
        return ResponseEntity.status(201).body("Cancel request received");
    }
}
