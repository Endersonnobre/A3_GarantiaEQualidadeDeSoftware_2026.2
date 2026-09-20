# Sistema de Controle de Estacionamento

Projeto acadêmico em Java 17 que implementa o núcleo do sistema descrito na ERS fornecida separadamente. A persistência é em memória e o relógio é injetável para permitir testes determinísticos.

## Executar

```bash
mvn test
mvn package
java -cp target/classes br.edu.estacionamento.app.Aplicacao
```

## Estrutura

- `dominio`: entidades, estados e tipos.
- `repositorio`: armazenamento em memória.
- `servico`: entrada, cobrança, pagamento, saída, reservas e mensalistas.
- `app`: demonstração simples.
- `src/test`: local destinado aos testes dos estudantes.

O trabalho consiste em derivar testes da especificação, implementar esses testes e registrar divergências observadas. A especificação, e não este README, é o oráculo funcional.
