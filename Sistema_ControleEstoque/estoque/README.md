# Sistema de Controle de Estoque

Projeto acadêmico em Java 17 que implementa o núcleo descrito na Especificação de Requisitos fornecida separadamente. A persistência é em memória e o relógio é injetável para permitir testes determinísticos.

## Execução

```bash
mvn test
mvn package
java -cp target/classes br.edu.estoque.app.Aplicacao
```

## Estrutura

- `dominio`: produtos, depósitos, lotes, reservas e movimentos.
- `repositorio`: armazenamento acadêmico em memória.
- `servico`: entradas, saídas, reservas, transferências e alertas.
- `app`: demonstração simples.
- `src/test`: local destinado aos testes dos estudantes.

A especificação é o oráculo funcional. Os estudantes devem derivar os testes a partir dela e registrar as divergências observadas.
