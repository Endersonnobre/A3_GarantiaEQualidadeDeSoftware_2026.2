package br.edu.estacionamento.servico;

import br.edu.estacionamento.dominio.*;
import br.edu.estacionamento.repositorio.BancoEmMemoria;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

public final class ServicoEstacionamento {
 private static final Pattern PLACA=Pattern.compile("[A-Z]{3}(?:[0-9]{4}|[0-9][A-Z][0-9]{2})");
 private final BancoEmMemoria banco; private final Clock relogio; private final CalculadoraTarifa tarifa;
 public ServicoEstacionamento(BancoEmMemoria banco,Clock relogio){this.banco=banco;this.relogio=relogio;this.tarifa=new CalculadoraTarifa();}

 public void cadastrarVaga(String codigo,TipoVaga tipo){
  Vaga vaga=new Vaga(codigo,tipo); if(banco.vagas.containsKey(vaga.codigo())) throw new IllegalStateException("Vaga duplicada"); banco.vagas.put(vaga.codigo(),vaga);
 }
 public void bloquearVaga(String codigo){Vaga v=vaga(codigo);if(v.estado()==EstadoVaga.OCUPADA)throw new IllegalStateException("Vaga ocupada");v.alterarEstado(EstadoVaga.BLOQUEADA);}
 public void desbloquearVaga(String codigo){Vaga v=vaga(codigo);if(v.estado()!=EstadoVaga.BLOQUEADA)throw new IllegalStateException("Estado inválido");v.alterarEstado(EstadoVaga.LIVRE);}

 public Ticket registrarEntrada(String placa,TipoVeiculo tipo){
  validarPlaca(placa);
  boolean duplicada=banco.tickets.values().stream().anyMatch(t->t.placa().equals(placa)&&(t.estado()==EstadoTicket.ABERTO||t.estado()==EstadoTicket.PAGO));
  if(duplicada)throw new IllegalStateException("Veículo já se encontra no estacionamento");
  Vaga vaga=banco.vagas.values().stream().filter(v->v.estado()!=EstadoVaga.OCUPADA).findFirst().orElseThrow(()->new IllegalStateException("Sem vaga"));
  ZonedDateTime agora=ZonedDateTime.now(relogio); Mensalista m=banco.mensalistas.get(placa); boolean mensalista=m!=null&&m.validoEm(agora);
  Ticket ticket=new Ticket(placa,tipo,vaga.codigo(),agora,mensalista); vaga.alterarEstado(EstadoVaga.OCUPADA); banco.tickets.put(ticket.id(),ticket); return ticket;
 }
 public BigDecimal calcular(UUID ticketId){Ticket t=ticket(ticketId);if(t.estado()!=EstadoTicket.ABERTO)throw new IllegalStateException("Estado inválido");return tarifa.calcular(t.entrada(),ZonedDateTime.now(relogio),t.mensalista());}
 public Pagamento pagar(UUID ticketId,BigDecimal valor,String meio,String chave){
  Pagamento anterior=banco.pagamentosPorChave.get(chave); if(anterior!=null)return anterior;
  Ticket t=ticket(ticketId);if(t.estado()!=EstadoTicket.ABERTO)throw new IllegalStateException("Estado inválido");BigDecimal devido=calcular(ticketId);
  if(valor.compareTo(devido)<0)throw new IllegalArgumentException("Valor divergente");
  ZonedDateTime agora=ZonedDateTime.now(relogio);Pagamento p=new Pagamento(UUID.randomUUID(),ticketId,valor,meio,chave,agora);banco.pagamentosPorChave.put(chave,p);t.pagar(valor,agora);return p;
 }
 public void registrarSaida(UUID ticketId){
  Ticket t=ticket(ticketId);if(t.estado()!=EstadoTicket.PAGO)throw new IllegalStateException("Saída não autorizada");
  ZonedDateTime agora=ZonedDateTime.now(relogio);if(ChronoUnit.MINUTES.between(t.pagoEm(),agora)>15)throw new IllegalStateException("Saída não autorizada");
  t.encerrar(agora);vaga(t.codigoVaga()).alterarEstado(EstadoVaga.LIVRE);
 }
 public void cancelar(UUID ticketId){Ticket t=ticket(ticketId);if(t.estado()==EstadoTicket.ENCERRADO)throw new IllegalStateException("Estado inválido");t.cancelar();vaga(t.codigoVaga()).alterarEstado(EstadoVaga.LIVRE);}
 public void cadastrarMensalista(String placa,ZonedDateTime inicio,ZonedDateTime fim){validarPlaca(placa);banco.mensalistas.put(placa,new Mensalista(placa,inicio,fim));}
 public Reserva reservar(String placa,String codigo,ZonedDateTime chegada){validarPlaca(placa);Vaga v=vaga(codigo);if(v.estado()!=EstadoVaga.LIVRE)throw new IllegalStateException("Vaga indisponível");Reserva r=new Reserva(placa,codigo,chegada);v.alterarEstado(EstadoVaga.RESERVADA);banco.reservas.put(r.id(),r);return r;}
 public void expirarReservas(){ZonedDateTime agora=ZonedDateTime.now(relogio);banco.reservas.values().stream().filter(r->!r.consumida()&&!r.expirada()&&!r.expiracao().isAfter(agora)).forEach(r->{r.expirar();vaga(r.vaga()).alterarEstado(EstadoVaga.LIVRE);});}
 public Map<EstadoVaga,Long> ocupacao(){Map<EstadoVaga,Long> m=new EnumMap<>(EstadoVaga.class);for(EstadoVaga e:EstadoVaga.values())m.put(e,0L);banco.vagas.values().forEach(v->m.put(v.estado(),m.get(v.estado())+1));return m;}
 private void validarPlaca(String placa){if(placa==null||!PLACA.matcher(placa).matches())throw new IllegalArgumentException("Placa inválida");}
 private Vaga vaga(String codigo){Vaga v=banco.vagas.get(codigo.trim().toUpperCase());if(v==null)throw new NoSuchElementException("Vaga não encontrada");return v;}
 private Ticket ticket(UUID id){Ticket t=banco.tickets.get(id);if(t==null)throw new NoSuchElementException("Ticket não encontrado");return t;}
}
