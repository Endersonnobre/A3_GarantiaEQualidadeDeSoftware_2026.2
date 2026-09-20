package br.edu.restaurante.service;
import static org.junit.jupiter.api.Assertions.*;import java.math.BigDecimal;import org.junit.jupiter.api.Test;
class ExemploSmokeTest {@Test void multiplicacaoMonetariaBasica(){assertEquals(new BigDecimal("96.00"),new BigDecimal("48.00").multiply(BigDecimal.valueOf(2)));}}
