package br.edu.estacionamento.dominio;
import java.time.ZonedDateTime;
public record Mensalista(String placa, ZonedDateTime inicio, ZonedDateTime fim) {
 public boolean validoEm(ZonedDateTime instante){return !instante.isBefore(inicio) && instante.isBefore(fim);}
}
