package br.edu.ticketflow.service;
import br.edu.ticketflow.domain.TipoIngresso;
import br.edu.ticketflow.exception.RegraNegocioException;
import java.math.*;
public final class CalculadoraPreco {
  public BigDecimal calcular(BigDecimal precoBase, TipoIngresso tipo, String cupom) {
    if (precoBase.signum()<0) throw new RegraNegocioException("Preço inválido");
    BigDecimal valor=tipo==TipoIngresso.MEIA_ENTRADA?precoBase.multiply(new BigDecimal("0.50")):precoBase;
    if (cupom!=null && cupom.equalsIgnoreCase("FESTA10")) valor=valor.subtract(new BigDecimal("10.00"));
    return valor.setScale(2, RoundingMode.HALF_UP);
  }
}
