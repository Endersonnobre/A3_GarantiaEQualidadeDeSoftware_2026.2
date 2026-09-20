package br.edu.estacionamento.dominio;
import java.math.BigDecimal; import java.time.ZonedDateTime; import java.util.UUID;
public record Pagamento(UUID id, UUID ticketId, BigDecimal valor, String meio, String chave, ZonedDateTime instante) {}
