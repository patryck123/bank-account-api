# 🏦 Bank Account API

Sistema bancário com operações de depósito, saque, transferência e extrato de transações.

## 📋 Sobre o Projeto

API que simula operações bancárias reais. Utiliza `@Transactional` para garantir a integridade das operações (se uma transferência falhar no meio, tudo é revertido). Cada conta tem número gerado automaticamente e histórico completo de movimentações.

## ✨ Funcionalidades

- ✅ Criar conta (Corrente ou Poupança)
- ✅ Consultar saldo
- ✅ Depósito
- ✅ Saque (com validação de saldo)
- ✅ Transferência entre contas (atômica com @Transactional)
- ✅ Extrato com histórico de transações
- ✅ Número de conta gerado automaticamente
- ✅ Listagem de todas as contas

## 🔗 Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/api/accounts` | Criar nova conta |
| GET | `/api/accounts` | Listar todas as contas |
| GET | `/api/accounts/{id}` | Buscar conta por ID |
| GET | `/api/accounts/{id}/balance` | Consultar saldo |
| POST | `/api/accounts/{id}/deposit` | Realizar depósito |
| POST | `/api/accounts/{id}/withdraw` | Realizar saque |
| POST | `/api/accounts/{id}/transfer` | Transferir para outra conta |
| GET | `/api/accounts/{id}/statement` | Extrato da conta |

## 🛠️ Tecnologias

- Java 17
- Spring Boot 3.2
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI 3
- Lombok

## ▶️ Como Executar

```bash
./mvnw spring-boot:run
```

## 📌 Regras de Negócio

- Saque e transferência são bloqueados se saldo insuficiente
- Transferências usam `@Transactional` — falha = rollback automático
- Número de conta é único e gerado no momento do cadastro
