package br.edu.ticketflow.domain;
import java.math.BigDecimal;
import java.util.Objects;
public record ItemPedido(String eventoId, String assento, TipoIngresso tipo, BigDecimal valor) {
  public ItemPedido { Objects.requireNonNull(eventoId); Objects.requireNonNull(assento); Objects.requireNonNull(tipo); Objects.requireNonNull(valor); }
}
