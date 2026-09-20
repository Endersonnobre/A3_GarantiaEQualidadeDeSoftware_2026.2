package br.edu.ticketflow.domain;
import java.time.LocalDate;
import java.util.Objects;
public record Cliente(String id, String nome, String email, String cpf, LocalDate nascimento) {
  public Cliente { 
      Objects.requireNonNull(id); 
      Objects.requireNonNull(nome); 
      Objects.requireNonNull(email); 
      Objects.requireNonNull(cpf); 
      Objects.requireNonNull(nascimento); }
}
