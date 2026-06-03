package com.patryck.bank.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name = "transactions") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "account_id") private Account account;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private TransactionType type;
    @Column(nullable = false, precision = 15, scale = 2) private BigDecimal amount;
    private String description;
    @Column(nullable = false) private LocalDateTime performedAt;
    @PrePersist protected void onCreate() { performedAt = LocalDateTime.now(); }
}
