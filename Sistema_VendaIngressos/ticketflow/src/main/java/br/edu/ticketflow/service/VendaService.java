package br.edu.ticketflow.service;
import br.edu.ticketflow.domain.*;
import br.edu.ticketflow.exception.RegraNegocioException;
import br.edu.ticketflow.repository.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
public final class VendaService {
  private final EventoRepository eventos; private final PedidoRepository pedidos; private final CalculadoraPreco precos; private final Clock relogio;
  public VendaService(EventoRepository eventos,PedidoRepository pedidos,CalculadoraPreco precos,Clock relogio){this.eventos=eventos;this.pedidos=pedidos;this.precos=precos;this.relogio=relogio;}
  public Pedido criarPedido(String clienteId,String eventoId,List<String> assentos,TipoIngresso tipo,String cupom){
    Evento evento=eventos.buscarPorId(eventoId).orElseThrow(()->new RegraNegocioException("Evento não encontrado"));
    LocalDateTime agora=LocalDateTime.now(relogio);
    if(evento.inicio().isBefore(agora)) throw new RegraNegocioException("Evento encerrado");
    if(assentos.isEmpty()||assentos.size()>6) throw new RegraNegocioException("Quantidade de ingressos inválida");
    for(String assento:assentos) if(!evento.assentos().contains(assento)) throw new RegraNegocioException("Assento inexistente: "+assento);
    for(Pedido existente:pedidos.listar()) if(existente.status()==StatusPedido.PAGO)
      for(ItemPedido item:existente.itens()) if(item.eventoId().equals(eventoId)&&assentos.contains(item.assento())) throw new RegraNegocioException("Assento indisponível");
    List<ItemPedido> itens=new ArrayList<>(); BigDecimal total=BigDecimal.ZERO;
    for(String assento:assentos){BigDecimal valor=precos.calcular(evento.precoBase(),tipo,cupom); itens.add(new ItemPedido(eventoId,assento,tipo,valor)); total=total.add(valor);}
    Pedido pedido=new Pedido(UUID.randomUUID().toString(),clienteId,itens,total,agora); pedidos.salvar(pedido); return pedido;
  }
  public void pagar(String pedidoId,BigDecimal valorRecebido){
    Pedido p=pedidos.buscarPorId(pedidoId).orElseThrow(()->new RegraNegocioException("Pedido não encontrado"));
    if(valorRecebido.compareTo(p.total())<0) throw new RegraNegocioException("Valor insuficiente");
    p.marcarPago(); pedidos.salvar(p);
  }
  public void cancelar(String pedidoId){
    Pedido p=pedidos.buscarPorId(pedidoId).orElseThrow(()->new RegraNegocioException("Pedido não encontrado")); p.cancelar(); pedidos.salvar(p);
  }
  public int expirarPedidos(){
    LocalDateTime limite=LocalDateTime.now(relogio).minusMinutes(15); int n=0;
    for(Pedido p:pedidos.listar()) if(p.status()==StatusPedido.AGUARDANDO_PAGAMENTO && p.criadoEm().isBefore(limite)){p.expirar();n++;}
    return n;
  }
}
