package br.edu.financas.service;
import br.edu.financas.domain.*;import br.edu.financas.exception.RegraNegocioException;import br.edu.financas.repository.Repositories;import java.math.*;import java.time.*;import java.util.*;
public class FinancaService {
 private final Repositories repo; public FinancaService(Repositories r){repo=r;}
 public Conta criarConta(String nome,BigDecimal saldo){if(nome==null||nome.isBlank())throw new RegraNegocioException("Nome obrigatório");Conta c=new Conta(nome,saldo);repo.contas().put(c.getId(),c);return c;}
 public Categoria criarCategoria(String nome,TipoLancamento tipo){Categoria c=new Categoria(nome,tipo);repo.categorias().put(c.id(),c);return c;}
 public Lancamento registrar(UUID contaId,UUID categoriaId,TipoLancamento tipo,BigDecimal valor,LocalDate data,StatusLancamento status){
  if(valor.compareTo(BigDecimal.ZERO)<0)throw new RegraNegocioException("Valor inválido"); Conta c=conta(contaId); Categoria g=categoria(categoriaId);
  if(g.tipo()!=tipo)throw new RegraNegocioException("Categoria incompatível"); Lancamento l=new Lancamento(contaId,categoriaId,tipo,valor.setScale(2,RoundingMode.DOWN),data,status);repo.lancamentos().put(l.getId(),l);
  if(status==StatusLancamento.EFETIVADO){if(tipo==TipoLancamento.RECEITA)c.creditar(l.getValor());else c.debitar(l.getValor());} return l;
 }
 public void cancelar(UUID id){Lancamento l=lancamento(id);l.cancelar();}
 public void transferir(UUID origemId,UUID destinoId,BigDecimal valor,String chave){
  Conta o=conta(origemId),d=conta(destinoId); if(valor.compareTo(BigDecimal.ZERO)<=0)throw new RegraNegocioException("Valor inválido"); if(repo.chavesTransferencia().contains(chave))return;
  o.debitar(valor);d.creditar(valor);repo.chavesTransferencia().add(chave);
 }
 public Orcamento definirOrcamento(UUID categoriaId,YearMonth mes,BigDecimal limite){Orcamento o=new Orcamento(categoriaId,mes,limite);repo.orcamentos().put(o.id(),o);return o;}
 public BigDecimal consumoOrcamento(UUID categoriaId,YearMonth mes){return repo.lancamentos().values().stream().filter(l->l.getCategoriaId().equals(categoriaId)&&YearMonth.from(l.getData()).equals(mes)&&l.getTipo()==TipoLancamento.DESPESA).map(Lancamento::getValor).reduce(BigDecimal.ZERO,BigDecimal::add);}
 public void aportarMeta(UUID metaId,BigDecimal valor){Meta m=repo.metas().get(metaId);if(m==null)throw new RegraNegocioException("Meta não encontrada");m.aportar(valor);}
 public BigDecimal saldoConsolidado(){return repo.contas().values().stream().map(Conta::getSaldo).reduce(BigDecimal.ZERO,BigDecimal::add);}
 private Conta conta(UUID id){Conta c=repo.contas().get(id);if(c==null)throw new RegraNegocioException("Conta não encontrada");return c;}private Categoria categoria(UUID id){Categoria c=repo.categorias().get(id);if(c==null)throw new RegraNegocioException("Categoria não encontrada");return c;}private Lancamento lancamento(UUID id){Lancamento l=repo.lancamentos().get(id);if(l==null)throw new RegraNegocioException("Lançamento não encontrado");return l;}
}
