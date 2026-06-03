package com.patryck.bank.service;
import com.patryck.bank.dto.*;
import com.patryck.bank.entity.*;
import com.patryck.bank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
@Service @RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    @Transactional
    public Account create(CreateAccountDTO dto) {
        if (accountRepository.existsByHolderCpf(dto.holderCpf()))
            throw new IllegalArgumentException("CPF já cadastrado");
        String number = String.format("%08d", new Random().nextInt(99999999));
        return accountRepository.save(Account.builder().accountNumber(number)
            .holderName(dto.holderName()).holderCpf(dto.holderCpf())
            .type(dto.type() != null ? dto.type() : AccountType.CHECKING).build());
    }
    public Account findById(Long id) { return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada")); }
    public List<Account> findAll() { return accountRepository.findAll(); }
    @Transactional
    public Account deposit(Long id, TransactionDTO dto) {
        Account account = findById(id);
        account.setBalance(account.getBalance().add(dto.amount()));
        transactionRepository.save(Transaction.builder().account(account).type(TransactionType.DEPOSIT).amount(dto.amount()).description(dto.description()).build());
        return accountRepository.save(account);
    }
    @Transactional
    public Account withdraw(Long id, TransactionDTO dto) {
        Account account = findById(id);
        if (account.getBalance().compareTo(dto.amount()) < 0) throw new IllegalArgumentException("Saldo insuficiente");
        account.setBalance(account.getBalance().subtract(dto.amount()));
        transactionRepository.save(Transaction.builder().account(account).type(TransactionType.WITHDRAWAL).amount(dto.amount()).description(dto.description()).build());
        return accountRepository.save(account);
    }
    @Transactional
    public void transfer(Long fromId, TransactionDTO dto) {
        Account from = findById(fromId);
        Account to = accountRepository.findByAccountNumber(dto.targetAccountNumber()).orElseThrow(() -> new RuntimeException("Conta destino não encontrada"));
        if (from.getBalance().compareTo(dto.amount()) < 0) throw new IllegalArgumentException("Saldo insuficiente");
        from.setBalance(from.getBalance().subtract(dto.amount()));
        to.setBalance(to.getBalance().add(dto.amount()));
        accountRepository.saveAll(List.of(from, to));
        transactionRepository.save(Transaction.builder().account(from).type(TransactionType.TRANSFER).amount(dto.amount()).description("Transferência para " + to.getAccountNumber()).build());
    }
    public List<Transaction> getStatement(Long id) { findById(id); return transactionRepository.findByAccountIdOrderByPerformedAtDesc(id); }
}
