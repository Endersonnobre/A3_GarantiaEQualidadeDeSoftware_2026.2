package br.edu.ticketflow.app;
import br.edu.ticketflow.domain.*;import br.edu.ticketflow.repository.*;import br.edu.ticketflow.service.*;
import java.math.BigDecimal;import java.time.*;import java.util.List;
public final class TicketFlowApplication {
  public static void main(String[] args){
    var eventos=new InMemoryEventoRepository(); 
    var pedidos=new InMemoryPedidoRepository();
    eventos.salvar(new Evento("EVT-001","Festival Java",LocalDateTime.now().plusDays(30),new BigDecimal("120.00"),List.of("A1","A2","A3","B1","B2","B3")));
    var vendas=new VendaService(eventos,pedidos,new CalculadoraPreco(),Clock.systemDefaultZone());
    var pedido=vendas.criarPedido("CLI-001","EVT-001",List.of("A1","A2"),TipoIngresso.INTEIRA,"FESTA10");
    vendas.pagar(pedido.id(),pedido.total());
    System.out.printf("Pedido %s | status=%s | total=R$ %s%n",pedido.id(),pedido.status(),pedido.total());
  }
}
