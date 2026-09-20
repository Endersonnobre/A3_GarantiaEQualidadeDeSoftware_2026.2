package br.edu.restaurante.service;
import br.edu.restaurante.domain.*;import br.edu.restaurante.exception.RegraNegocioException;import br.edu.restaurante.repository.Repositories;
import java.math.*;import java.time.*;import java.util.*;
public final class PedidoService {
 public record SolicitacaoItem(String itemId,int quantidade){}
 private final Repositories.Cardapio cardapio;private final Repositories.Pedidos pedidos;private final Clock relogio;
 public PedidoService(Repositories.Cardapio cardapio,Repositories.Pedidos pedidos,Clock relogio){this.cardapio=cardapio;this.pedidos=pedidos;this.relogio=relogio;}
 public Pedido criar(String clienteId,String mesaId,List<SolicitacaoItem> solicitados,String cupom){
  if(solicitados.size()>20)throw new RegraNegocioException("Muitos itens");
  List<ItemPedido> itens=new ArrayList<>();BigDecimal total=BigDecimal.ZERO;
  for(SolicitacaoItem s:solicitados){ItemCardapio c=cardapio.porId(s.itemId()).orElseThrow(()->new RegraNegocioException("Item inexistente"));
   if(!c.disponivel()||s.quantidade()<1||s.quantidade()>10||c.estoque()<s.quantidade())throw new RegraNegocioException("Item indisponível");
   BigDecimal valor=c.preco().multiply(BigDecimal.valueOf(s.quantidade()));
   if("ALMOCO10".equalsIgnoreCase(cupom))valor=valor.subtract(new BigDecimal("10.00"));
   itens.add(new ItemPedido(c.id(),s.quantidade(),c.preco()));total=total.add(valor);
  }
  Pedido p=new Pedido(UUID.randomUUID().toString(),clienteId,mesaId,itens,total.setScale(2,RoundingMode.HALF_UP),LocalDateTime.now(relogio));pedidos.salvar(p);return p;
 }
 public void enviarCozinha(String id){Pedido p=obter(id);p.enviarCozinha();pedidos.salvar(p);}
 public void iniciarPreparo(String id){Pedido p=obter(id);p.iniciarPreparo();pedidos.salvar(p);}
 public void marcarPronto(String id){Pedido p=obter(id);p.marcarPronto();pedidos.salvar(p);}
 public void pagar(String id,BigDecimal valor){Pedido p=obter(id);if(valor.compareTo(p.total())<0)throw new RegraNegocioException("Valor insuficiente");p.pagar();pedidos.salvar(p);}
 public void cancelar(String id){Pedido p=obter(id);p.cancelar();pedidos.salvar(p);}
 private Pedido obter(String id){return pedidos.porId(id).orElseThrow(()->new RegraNegocioException("Pedido não encontrado"));}
}
