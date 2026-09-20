package br.edu.estoque.dominio;
import java.math.BigDecimal;import java.time.LocalDate;import java.time.ZonedDateTime;import java.util.UUID;
public final class Lote {
 private final UUID id=UUID.randomUUID();private final String numero;private final String sku;private final String deposito;private BigDecimal quantidade;private final BigDecimal custo;private final LocalDate validade;private final ZonedDateTime entrada;
 public Lote(String numero,String sku,String deposito,BigDecimal quantidade,BigDecimal custo,LocalDate validade,ZonedDateTime entrada){this.numero=numero;this.sku=sku;this.deposito=deposito;this.quantidade=quantidade;this.custo=custo;this.validade=validade;this.entrada=entrada;}
 public UUID id(){return id;}public String numero(){return numero;}public String sku(){return sku;}public String deposito(){return deposito;}public BigDecimal quantidade(){return quantidade;}public BigDecimal custo(){return custo;}public LocalDate validade(){return validade;}public ZonedDateTime entrada(){return entrada;}
 public void adicionar(BigDecimal q){quantidade=quantidade.add(q);}public void retirar(BigDecimal q){quantidade=quantidade.subtract(q);}
}
