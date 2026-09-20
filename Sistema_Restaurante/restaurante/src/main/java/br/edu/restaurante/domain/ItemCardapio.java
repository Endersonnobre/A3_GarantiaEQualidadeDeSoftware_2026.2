package br.edu.restaurante.domain;
import java.math.BigDecimal;
import java.util.Objects;
public record ItemCardapio(String id,String nome,BigDecimal preco,int estoque,boolean disponivel){
 public ItemCardapio { Objects.requireNonNull(id);Objects.requireNonNull(nome);Objects.requireNonNull(preco); }
 public ItemCardapio comEstoque(int novo){return new ItemCardapio(id,nome,preco,novo,disponivel);}
}
