package br.edu.financas.domain;
import java.math.BigDecimal; import java.util.UUID;
public class Conta {
 private final UUID id=UUID.randomUUID(); private final String nome; private BigDecimal saldo; private boolean ativa=true;
 public Conta(String nome, BigDecimal saldoInicial){this.nome=nome;this.saldo=saldoInicial;}
 public UUID getId(){return id;} public String getNome(){return nome;} public BigDecimal getSaldo(){return saldo;} public boolean isAtiva(){return ativa;}
 public void creditar(BigDecimal v){saldo=saldo.add(v);} public void debitar(BigDecimal v){saldo=saldo.subtract(v);} public void desativar(){ativa=false;}
}
