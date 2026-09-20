package br.edu.financas.domain;
import java.math.BigDecimal; import java.time.LocalDate; import java.util.UUID;
public class Lancamento {
 private final UUID id=UUID.randomUUID(); private final UUID contaId; private final UUID categoriaId; private final TipoLancamento tipo; private final BigDecimal valor; private final LocalDate data; private StatusLancamento status;
 public Lancamento(UUID contaId,UUID categoriaId,TipoLancamento tipo,BigDecimal valor,LocalDate data,StatusLancamento status){this.contaId=contaId;this.categoriaId=categoriaId;this.tipo=tipo;this.valor=valor;this.data=data;this.status=status;}
 public UUID getId(){return id;} public UUID getContaId(){return contaId;} public UUID getCategoriaId(){return categoriaId;} public TipoLancamento getTipo(){return tipo;} public BigDecimal getValor(){return valor;} public LocalDate getData(){return data;} public StatusLancamento getStatus(){return status;} public void cancelar(){status=StatusLancamento.CANCELADO;}
}
