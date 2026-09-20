package br.edu.restaurante.domain;
import java.math.BigDecimal;import java.time.LocalDateTime;import java.util.*;
public final class Pedido {
 private final String id,clienteId,mesaId; private final List<ItemPedido> itens; private final LocalDateTime criadoEm; private BigDecimal total; private StatusPedido status;
 public Pedido(String id,String clienteId,String mesaId,List<ItemPedido> itens,BigDecimal total,LocalDateTime criadoEm){
  this.id=id;this.clienteId=clienteId;this.mesaId=mesaId;this.itens=List.copyOf(itens);this.total=total;this.criadoEm=criadoEm;this.status=StatusPedido.ABERTO;
 }
 public String id(){return id;} public String clienteId(){return clienteId;} public String mesaId(){return mesaId;} public List<ItemPedido> itens(){return itens;}
 public BigDecimal total(){return total;} public LocalDateTime criadoEm(){return criadoEm;} public StatusPedido status(){return status;}
 public void enviarCozinha(){status=StatusPedido.ENVIADO_COZINHA;} public void iniciarPreparo(){status=StatusPedido.EM_PREPARO;}
 public void marcarPronto(){status=StatusPedido.PRONTO;} public void entregar(){status=StatusPedido.ENTREGUE;} public void pagar(){status=StatusPedido.PAGO;} public void cancelar(){status=StatusPedido.CANCELADO;}
}
