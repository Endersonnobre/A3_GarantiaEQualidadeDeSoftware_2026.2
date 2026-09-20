package br.edu.estoque.dominio;
import java.math.BigDecimal; import java.util.Objects; import java.util.UUID;
public final class Produto {
 private final UUID id=UUID.randomUUID(); private final String sku; private final String nome; private final Unidade unidade; private final BigDecimal estoqueMinimo; private boolean ativo=true;
 public Produto(String sku,String nome,Unidade unidade,BigDecimal estoqueMinimo){
  if(sku==null||!sku.matches("[A-Za-z0-9_-]{3,20}"))throw new IllegalArgumentException("SKU inválido");
  if(nome==null||nome.isBlank())throw new IllegalArgumentException("Nome inválido");
  if(estoqueMinimo==null||estoqueMinimo.signum()<0)throw new IllegalArgumentException("Mínimo inválido");
  this.sku=sku;this.nome=nome.trim();this.unidade=Objects.requireNonNull(unidade);this.estoqueMinimo=estoqueMinimo;
 }
 public UUID id(){return id;} public String sku(){return sku;} public String nome(){return nome;} public Unidade unidade(){return unidade;} public BigDecimal estoqueMinimo(){return estoqueMinimo;} public boolean ativo(){return ativo;} public void inativar(){ativo=false;}
}
