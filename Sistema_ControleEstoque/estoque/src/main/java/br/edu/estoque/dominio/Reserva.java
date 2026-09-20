package br.edu.estoque.dominio;
import java.math.BigDecimal;import java.time.ZonedDateTime;import java.util.UUID;
public final class Reserva {
 private final UUID id=UUID.randomUUID();private final String sku;private final String deposito;private final BigDecimal quantidade;private final ZonedDateTime expiraEm;private EstadoReserva estado=EstadoReserva.ATIVA;
 public Reserva(String sku,String deposito,BigDecimal quantidade,ZonedDateTime expiraEm){this.sku=sku;this.deposito=deposito;this.quantidade=quantidade;this.expiraEm=expiraEm;}
 public UUID id(){return id;}public String sku(){return sku;}public String deposito(){return deposito;}public BigDecimal quantidade(){return quantidade;}public ZonedDateTime expiraEm(){return expiraEm;}public EstadoReserva estado(){return estado;}public void estado(EstadoReserva e){estado=e;}
}
