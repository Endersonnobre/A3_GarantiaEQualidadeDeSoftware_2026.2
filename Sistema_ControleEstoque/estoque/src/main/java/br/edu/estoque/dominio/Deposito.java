package br.edu.estoque.dominio;
import java.util.UUID;
public final class Deposito {
 private final UUID id=UUID.randomUUID();private final String codigo;private final String nome;private boolean ativo=true;
 public Deposito(String codigo,String nome){if(codigo==null||codigo.isBlank())throw new IllegalArgumentException("Código inválido");this.codigo=codigo.trim().toUpperCase();this.nome=nome;}
 public UUID id(){return id;}public String codigo(){return codigo;}public String nome(){return nome;}public boolean ativo(){return ativo;}public void inativar(){ativo=false;}
}
