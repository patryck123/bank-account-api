# Bank Account API

Sistema bancário simplificado com operações de depósito, saque, transferência entre contas e extrato. Desenvolvido com Spring Boot e PostgreSQL, com controle transacional garantindo consistência dos dados.

## Tecnologias
- Java 17 · Spring Boot 3.2 · Spring Data JPA · PostgreSQL · Maven · Swagger/OpenAPI

## Funcionalidades
- Criação de contas (Corrente, Poupança, Salário)
- Depósito e Saque com validação de saldo
- Transferência entre contas com transação atômica (`@Transactional`)
- Extrato de transações ordenado por data
- Geração automática de número de conta

## Como Executar
```bash
mvn spring-boot:run
# Acesse: http://localhost:8085/swagger-ui.html
```

## Autor
**Patryck Martins Langsdorff** — Java Back End Developer Junior
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue)](https://www.linkedin.com/in/patryck-martins-langsdorff)
