# TicketFlow

Aplicação didática de venda de ingressos em Java 17. O projeto foi preparado para que estudantes derivem e implementem testes a partir da especificação de requisitos fornecida separadamente.

## Executar

```bash
mvn clean test
mvn exec:java
```

## Estrutura

- `domain`: entidades e enumerações do negócio.
- `repository`: contratos e persistência em memória.
- `service`: casos de uso e regras de negócio.
- `app`: demonstração executável.
- `src/test`: ponto inicial para a suíte dos estudantes.

Não presuma que o comportamento atual está correto. A especificação é a fonte de verdade.
