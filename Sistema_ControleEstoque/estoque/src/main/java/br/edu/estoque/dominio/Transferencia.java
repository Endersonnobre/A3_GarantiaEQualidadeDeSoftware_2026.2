package br.edu.estoque.dominio;
import java.math.BigDecimal;import java.time.ZonedDateTime;import java.util.UUID;
public record Transferencia(UUID id,String sku,String origem,String destino,BigDecimal quantidade,String chave,ZonedDateTime instante){}
