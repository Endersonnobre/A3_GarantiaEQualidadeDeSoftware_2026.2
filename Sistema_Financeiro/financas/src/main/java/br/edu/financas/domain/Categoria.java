package br.edu.financas.domain;
import java.util.UUID;
public record Categoria(UUID id,String nome,TipoLancamento tipo,boolean ativa){public Categoria(String n,TipoLancamento t){this(UUID.randomUUID(),n,t,true);}}
