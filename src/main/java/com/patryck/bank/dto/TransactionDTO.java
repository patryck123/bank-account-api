package com.patryck.bank.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
public record TransactionDTO(
    @NotNull @Positive BigDecimal amount,
    String description,
    String targetAccountNumber
) {}
