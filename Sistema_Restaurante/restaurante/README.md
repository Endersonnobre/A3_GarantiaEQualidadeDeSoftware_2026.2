# Restaurante Acadêmico

Aplicação didática Java 17 baseada na especificação de requisitos fornecida separadamente. A especificação é a fonte de verdade: o comportamento observado deve ser confrontado com ela.

## Execução

```bash
mvn clean test
mvn exec:java
```

## Pacotes

- `domain`: entidades e estados.
- `repository`: contratos e implementações em memória.
- `service`: regras e casos de uso.
- `app`: cenário demonstrativo.
- `src/test`: ponto inicial para os testes dos estudantes.

Amplie a suíte com testes unitários, parametrizados, de integração, transição de estados e concorrência.
