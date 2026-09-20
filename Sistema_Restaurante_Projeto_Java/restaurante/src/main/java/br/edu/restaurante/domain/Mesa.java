package br.edu.restaurante.domain;
public record Mesa(String id, int capacidade, boolean ativa) {
 public Mesa { if(id==null||id.isBlank()) throw new IllegalArgumentException("id"); if(capacidade<=0) throw new IllegalArgumentException("capacidade"); }
}
