package br.edu.restaurante.domain;
import java.time.LocalDateTime;
public final class Reserva {
 private final String id,clienteId,mesaId; private final int pessoas; private final LocalDateTime horario,criadaEm; private StatusReserva status;
 public Reserva(String id,String clienteId,String mesaId,int pessoas,LocalDateTime horario,LocalDateTime criadaEm){
  this.id=id;this.clienteId=clienteId;this.mesaId=mesaId;this.pessoas=pessoas;this.horario=horario;this.criadaEm=criadaEm;this.status=StatusReserva.PENDENTE;
 }
 public String id(){return id;} public String clienteId(){return clienteId;} public String mesaId(){return mesaId;} public int pessoas(){return pessoas;}
 public LocalDateTime horario(){return horario;} public LocalDateTime criadaEm(){return criadaEm;} public StatusReserva status(){return status;}
 public void confirmar(){status=StatusReserva.CONFIRMADA;} public void ocupar(){status=StatusReserva.OCUPADA;} public void concluir(){status=StatusReserva.CONCLUIDA;}
 public void cancelar(){status=StatusReserva.CANCELADA;} public void marcarAusencia(){status=StatusReserva.NAO_COMPARECEU;}
}
