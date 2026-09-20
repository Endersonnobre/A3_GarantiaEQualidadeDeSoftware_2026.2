package br.edu.estacionamento.app;

import br.edu.estacionamento.dominio.*;
import br.edu.estacionamento.repositorio.BancoEmMemoria;
import br.edu.estacionamento.servico.ServicoEstacionamento;
import java.math.BigDecimal;
import java.time.*;

public final class Aplicacao {
 public static void main(String[] args){
  BancoEmMemoria banco=new BancoEmMemoria();Clock relogio=Clock.fixed(Instant.parse("2026-09-20T12:00:00Z"),ZoneId.of("America/Sao_Paulo"));
  ServicoEstacionamento servico=new ServicoEstacionamento(banco,relogio);servico.cadastrarVaga("C01",TipoVaga.CARRO);servico.cadastrarVaga("M01",TipoVaga.MOTO);
  Ticket ticket=servico.registrarEntrada("ABC1D23",TipoVeiculo.CARRO);BigDecimal valor=servico.calcular(ticket.id());
  System.out.printf("Ticket %s | vaga %s | valor %s%n",ticket.id(),ticket.codigoVaga(),valor);
 }
}
