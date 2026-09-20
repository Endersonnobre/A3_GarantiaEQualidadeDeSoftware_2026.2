package br.edu.financas.domain;
import java.math.BigDecimal; import java.time.LocalDate; import java.util.UUID;
public class Meta {private final UUID id=UUID.randomUUID();private final String nome;private final BigDecimal alvo;private BigDecimal acumulado=BigDecimal.ZERO;private final LocalDate prazo;
 public Meta(String n,BigDecimal a,LocalDate p){nome=n;alvo=a;prazo=p;} public UUID getId(){return id;} public BigDecimal getAlvo(){return alvo;} public BigDecimal getAcumulado(){return acumulado;} public void aportar(BigDecimal v){acumulado=acumulado.add(v);} public boolean concluida(){return acumulado.compareTo(alvo)>=0;}}
