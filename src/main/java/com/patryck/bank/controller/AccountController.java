package com.patryck.bank.controller;
import com.patryck.bank.dto.*;
import com.patryck.bank.entity.*;
import com.patryck.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/accounts") @RequiredArgsConstructor
@Tag(name = "Contas Bancárias", description = "Operações bancárias: depósito, saque e transferência")
public class AccountController {
    private final AccountService accountService;
    @PostMapping public ResponseEntity<Account> create(@Valid @RequestBody CreateAccountDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(dto)); }
    @GetMapping public ResponseEntity<List<Account>> findAll() { return ResponseEntity.ok(accountService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Account> findById(@PathVariable Long id) { return ResponseEntity.ok(accountService.findById(id)); }
    @PostMapping("/{id}/deposit") @Operation(summary = "Realizar depósito") public ResponseEntity<Account> deposit(@PathVariable Long id, @Valid @RequestBody TransactionDTO dto) { return ResponseEntity.ok(accountService.deposit(id, dto)); }
    @PostMapping("/{id}/withdraw") @Operation(summary = "Realizar saque") public ResponseEntity<Account> withdraw(@PathVariable Long id, @Valid @RequestBody TransactionDTO dto) { return ResponseEntity.ok(accountService.withdraw(id, dto)); }
    @PostMapping("/{id}/transfer") @Operation(summary = "Realizar transferência") public ResponseEntity<Void> transfer(@PathVariable Long id, @Valid @RequestBody TransactionDTO dto) { accountService.transfer(id, dto); return ResponseEntity.noContent().build(); }
    @GetMapping("/{id}/statement") @Operation(summary = "Extrato da conta") public ResponseEntity<List<Transaction>> getStatement(@PathVariable Long id) { return ResponseEntity.ok(accountService.getStatement(id)); }
}
