package com.patryck.bank.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name = "accounts") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String accountNumber;
    @Column(nullable = false) private String holderName;
    @Column(nullable = false) private String holderCpf;
    @Column(nullable = false, precision = 15, scale = 2) @Builder.Default private BigDecimal balance = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING) @Builder.Default private AccountType type = AccountType.CHECKING;
    @Builder.Default private Boolean active = true;
    @Column(nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); }
}
