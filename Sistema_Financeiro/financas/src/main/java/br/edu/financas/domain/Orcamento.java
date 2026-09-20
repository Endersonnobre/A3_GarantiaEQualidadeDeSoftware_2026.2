package br.edu.financas.domain;
import java.math.BigDecimal; import java.time.YearMonth; import java.util.UUID;
public record Orcamento(UUID id,UUID categoriaId,YearMonth mes,BigDecimal limite){public Orcamento(UUID c,YearMonth m,BigDecimal l){this(UUID.randomUUID(),c,m,l);}}
