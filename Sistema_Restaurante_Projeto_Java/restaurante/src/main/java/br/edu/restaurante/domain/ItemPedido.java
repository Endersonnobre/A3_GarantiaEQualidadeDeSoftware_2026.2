package br.edu.restaurante.domain;
import java.math.BigDecimal;
public record ItemPedido(String itemId,int quantidade,BigDecimal precoUnitario){
 public BigDecimal subtotal(){return precoUnitario.multiply(BigDecimal.valueOf(quantidade));}
}
