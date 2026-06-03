package com.patryck.bank.repository;
import com.patryck.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountIdOrderByPerformedAtDesc(Long accountId);
}
