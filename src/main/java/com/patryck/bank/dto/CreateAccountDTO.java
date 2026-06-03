package com.patryck.bank.dto;
import com.patryck.bank.entity.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
public record CreateAccountDTO(
    @NotBlank String holderName,
    @NotBlank @Pattern(regexp = "\\d{11}") String holderCpf,
    AccountType type
) {}
