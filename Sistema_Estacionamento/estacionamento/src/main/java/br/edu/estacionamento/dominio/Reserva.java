package br.edu.estacionamento.dominio;
import java.time.ZonedDateTime; import java.util.UUID;
public final class Reserva {
 private final UUID id=UUID.randomUUID(); private final String placa; private final String vaga; private final ZonedDateTime chegada; private boolean consumida; private boolean expirada;
 public Reserva(String placa,String vaga,ZonedDateTime chegada){this.placa=placa;this.vaga=vaga;this.chegada=chegada;}
 public UUID id(){return id;} public String placa(){return placa;} public String vaga(){return vaga;} public ZonedDateTime chegada(){return chegada;}
 public ZonedDateTime expiracao(){return chegada.plusMinutes(15);} public boolean consumida(){return consumida;} public boolean expirada(){return expirada;}
 public void consumir(){consumida=true;} public void expirar(){expirada=true;}
}
