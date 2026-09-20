package br.edu.estoque.app;
import br.edu.estoque.dominio.*;import br.edu.estoque.repositorio.BancoEmMemoria;import br.edu.estoque.servico.ServicoEstoque;import java.math.BigDecimal;import java.time.*;
public final class Aplicacao {
 public static void main(String[] args){BancoEmMemoria banco=new BancoEmMemoria();Clock clock=Clock.fixed(Instant.parse("2026-09-20T12:00:00Z"),ZoneId.of("America/Sao_Paulo"));ServicoEstoque s=new ServicoEstoque(banco,clock);s.cadastrarProduto("SKU-001","Cabo de rede",Unidade.UN,new BigDecimal("5.000"));s.cadastrarDeposito("CD-01","Centro de distribuição");s.registrarEntrada("SKU-001","CD-01","L001",new BigDecimal("20.000"),new BigDecimal("12.50"),LocalDate.of(2027,1,31));System.out.printf("Saldo físico: %s | disponível: %s%n",s.saldoFisico("SKU-001","CD-01"),s.saldoDisponivel("SKU-001","CD-01"));}
}
