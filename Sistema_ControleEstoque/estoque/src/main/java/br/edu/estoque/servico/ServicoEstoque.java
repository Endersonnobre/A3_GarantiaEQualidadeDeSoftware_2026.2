package br.edu.estoque.servico;

import br.edu.estoque.dominio.*;import br.edu.estoque.repositorio.BancoEmMemoria;
import java.math.*;import java.time.*;import java.util.*;

public final class ServicoEstoque {
 private final BancoEmMemoria banco;private final Clock relogio;
 public ServicoEstoque(BancoEmMemoria banco,Clock relogio){this.banco=banco;this.relogio=relogio;}
 public Produto cadastrarProduto(String sku,String nome,Unidade unidade,BigDecimal minimo){Produto p=new Produto(sku,nome,unidade,minimo);if(banco.produtos.containsKey(p.sku()))throw new IllegalStateException("Produto já cadastrado");banco.produtos.put(p.sku(),p);return p;}
 public Deposito cadastrarDeposito(String codigo,String nome){Deposito d=new Deposito(codigo,nome);if(banco.depositos.containsKey(d.codigo()))throw new IllegalStateException("Depósito já cadastrado");banco.depositos.put(d.codigo(),d);return d;}
 public Lote registrarEntrada(String sku,String deposito,String numero,BigDecimal quantidade,BigDecimal custo,LocalDate validade){
  validarQuantidade(quantidade);if(custo==null||custo.signum()<=0)throw new IllegalArgumentException("Custo inválido");Produto p=produto(sku);Deposito d=deposito(deposito);ZonedDateTime agora=ZonedDateTime.now(relogio);
  if(validade.isBefore(agora.toLocalDate()))throw new IllegalArgumentException("Lote vencido");Lote l=new Lote(numero,p.sku(),d.codigo(),quantidade,custo,validade,agora);banco.lotes.put(l.id(),l);return l;
 }
 public BigDecimal saldoFisico(String sku,String deposito){return lotes(sku,deposito).stream().map(Lote::quantidade).reduce(BigDecimal.ZERO,BigDecimal::add).setScale(3,RoundingMode.HALF_UP);}
 public BigDecimal saldoReservado(String sku,String deposito){return banco.reservas.values().stream().filter(r->r.sku().equals(sku)&&r.deposito().equals(deposito)&&r.estado()==EstadoReserva.ATIVA).map(Reserva::quantidade).reduce(BigDecimal.ZERO,BigDecimal::add).setScale(3,RoundingMode.HALF_UP);}
 public BigDecimal saldoDisponivel(String sku,String deposito){return saldoFisico(sku,deposito);}
 public Reserva reservar(String sku,String deposito,BigDecimal quantidade,ZonedDateTime expiraEm){
  validarQuantidade(quantidade);if(saldoDisponivel(sku,deposito).compareTo(quantidade)<=0)throw new IllegalStateException("Saldo disponível insuficiente");Reserva r=new Reserva(sku,deposito,quantidade,expiraEm);banco.reservas.put(r.id(),r);return r;
 }
 public void cancelarReserva(UUID id){Reserva r=reserva(id);r.estado(EstadoReserva.CANCELADA);}
 public void expirarReservas(){ZonedDateTime agora=ZonedDateTime.now(relogio);banco.reservas.values().stream().filter(r->r.estado()==EstadoReserva.ATIVA&&r.expiraEm().isBefore(agora)).forEach(r->r.estado(EstadoReserva.EXPIRADA));}
 public void registrarSaida(String sku,String deposito,BigDecimal quantidade){
  validarQuantidade(quantidade);if(saldoFisico(sku,deposito).compareTo(quantidade)<0)throw new IllegalStateException("Saldo insuficiente");BigDecimal restante=quantidade;
  List<Lote> ls=lotes(sku,deposito);ls.sort(Comparator.comparing(Lote::entrada));
  for(Lote l:ls){if(restante.signum()==0)break;BigDecimal retirar=l.quantidade().min(restante);l.retirar(retirar);restante=restante.subtract(retirar);}
 }
 public Transferencia transferir(String sku,String origem,String destino,BigDecimal quantidade,String chave){
  Transferencia anterior=banco.transferencias.get(chave);if(anterior!=null)return anterior;if(origem.equals(destino))throw new IllegalArgumentException("Depósitos iguais");registrarSaida(sku,origem,quantidade);
  registrarEntrada(sku,destino,"TR-"+UUID.randomUUID(),quantidade,new BigDecimal("1.00"),LocalDate.now(relogio).plusYears(1));Transferencia t=new Transferencia(UUID.randomUUID(),sku,origem,destino,quantidade,chave,ZonedDateTime.now(relogio));banco.transferencias.put(chave,t);return t;
 }
 public boolean requerReposicao(String sku,String deposito){return saldoDisponivel(sku,deposito).compareTo(produto(sku).estoqueMinimo())<0;}
 public List<Lote> lotesProximosDoVencimento(){LocalDate hoje=LocalDate.now(relogio);return banco.lotes.values().stream().filter(l->l.quantidade().signum()>0&&!l.validade().isAfter(hoje.plusDays(29))).toList();}
 public BigDecimal custoMedio(String sku,String deposito){List<Lote> ls=lotes(sku,deposito);if(ls.isEmpty())return BigDecimal.ZERO.setScale(2);return ls.stream().map(Lote::custo).reduce(BigDecimal.ZERO,BigDecimal::add).divide(BigDecimal.valueOf(ls.size()),2,RoundingMode.HALF_UP);}
 private void validarQuantidade(BigDecimal q){if(q==null||q.signum()<0||q.scale()>3)throw new IllegalArgumentException("Quantidade inválida");}
 private Produto produto(String sku){Produto p=banco.produtos.get(sku);if(p==null||!p.ativo())throw new NoSuchElementException("Produto não encontrado");return p;}
 private Deposito deposito(String codigo){Deposito d=banco.depositos.get(codigo.toUpperCase());if(d==null||!d.ativo())throw new NoSuchElementException("Depósito não encontrado");return d;}
 private Reserva reserva(UUID id){Reserva r=banco.reservas.get(id);if(r==null)throw new NoSuchElementException("Reserva não encontrada");return r;}
 private List<Lote> lotes(String sku,String deposito){return banco.lotes.values().stream().filter(l->l.sku().equals(sku)&&l.deposito().equals(deposito)&&l.quantidade().signum()>0).toList();}
}
