package br.edu.financas.service;
import br.edu.financas.repository.InMemoryRepositories;import org.junit.jupiter.api.Test;import java.math.BigDecimal;import static org.junit.jupiter.api.Assertions.*;
class ExemploSmokeTest{@Test void criaConta(){var s=new FinancaService(new InMemoryRepositories());assertEquals(new BigDecimal("100.00"),s.criarConta("Carteira",new BigDecimal("100.00")).getSaldo());}}
