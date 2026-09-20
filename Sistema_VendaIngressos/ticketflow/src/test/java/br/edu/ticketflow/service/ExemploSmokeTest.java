package br.edu.ticketflow.service;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import br.edu.ticketflow.domain.TipoIngresso;
class ExemploSmokeTest {
  @Test void calculaIngressoInteiroSemCupom(){assertEquals(new BigDecimal("100.00"),new CalculadoraPreco().calcular(new BigDecimal("100.00"),TipoIngresso.INTEIRA,null));}
}
