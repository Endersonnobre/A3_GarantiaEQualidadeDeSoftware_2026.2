package br.edu.estacionamento.servico;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

public final class CalculadoraTarifa {
 private static final BigDecimal PRIMEIRA=new BigDecimal("8.00");
 private static final BigDecimal ADICIONAL=new BigDecimal("5.00");
 private static final BigDecimal TETO=new BigDecimal("40.00");

 public BigDecimal calcular(ZonedDateTime entrada,ZonedDateTime fim,boolean mensalista){
  if(fim.isBefore(entrada)) throw new IllegalArgumentException("Instante inválido");
  if(mensalista) return BigDecimal.ZERO.setScale(2);
  long minutos=Duration.between(entrada,fim).toMinutes();
  if(minutos<15) return BigDecimal.ZERO.setScale(2);
  long horasAdicionais=Math.max(0,(minutos-60)/60);
  BigDecimal valor=PRIMEIRA.add(ADICIONAL.multiply(BigDecimal.valueOf(horasAdicionais)));
  return valor.setScale(2);
 }
 public BigDecimal calcularTicketPerdido(ZonedDateTime entrada,ZonedDateTime fim){
  return calcular(entrada,fim,false).min(new BigDecimal("60.00"));
 }
}
