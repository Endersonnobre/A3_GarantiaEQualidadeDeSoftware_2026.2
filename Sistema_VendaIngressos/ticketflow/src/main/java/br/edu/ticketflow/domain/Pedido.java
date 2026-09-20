package br.edu.ticketflow.domain;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
public final class Pedido {
  private final String id, clienteId; private final List<ItemPedido> itens; private final LocalDateTime criadoEm;
  private BigDecimal total; private StatusPedido status;
  public Pedido(String id,String clienteId,List<ItemPedido> itens,BigDecimal total,LocalDateTime criadoEm){
    this.id=id;this.clienteId=clienteId;this.itens=List.copyOf(itens);this.total=total;this.criadoEm=criadoEm;this.status=StatusPedido.AGUARDANDO_PAGAMENTO;
  }
  public String id(){return id;} public String clienteId(){return clienteId;} public List<ItemPedido> itens(){return itens;}
  public BigDecimal total(){return total;} public LocalDateTime criadoEm(){return criadoEm;} public StatusPedido status(){return status;}
  public void marcarPago(){status=StatusPedido.PAGO;} public void cancelar(){status=StatusPedido.CANCELADO;} public void expirar(){status=StatusPedido.EXPIRADO;}
}
