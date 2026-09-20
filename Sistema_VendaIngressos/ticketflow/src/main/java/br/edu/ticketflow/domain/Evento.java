package br.edu.ticketflow.domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
public final class Evento {
  private final String id; private final String nome; private final LocalDateTime inicio;
  private final BigDecimal precoBase; private final Set<String> assentos;
  public Evento(String id, String nome, LocalDateTime inicio, BigDecimal precoBase, Collection<String> assentos) {
    this.id=Objects.requireNonNull(id); this.nome=Objects.requireNonNull(nome); this.inicio=Objects.requireNonNull(inicio);
    this.precoBase=Objects.requireNonNull(precoBase); this.assentos=Set.copyOf(assentos);
  }
  public String id(){return id;} public String nome(){return nome;} public LocalDateTime inicio(){return inicio;}
  public BigDecimal precoBase(){return precoBase;} public Set<String> assentos(){return assentos;}
}
