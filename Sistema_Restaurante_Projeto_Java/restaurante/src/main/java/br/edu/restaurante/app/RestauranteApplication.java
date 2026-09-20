package br.edu.restaurante.app;
import br.edu.restaurante.domain.*;import br.edu.restaurante.repository.*;import br.edu.restaurante.service.*;
import java.math.BigDecimal;import java.time.*;import java.util.List;
public final class RestauranteApplication {
 public static void main(String[] args){
  var mesas=new InMemoryRepositories.Mesas();var reservas=new InMemoryRepositories.Reservas();var cardapio=new InMemoryRepositories.Cardapio();var pedidos=new InMemoryRepositories.Pedidos();
  mesas.salvar(new Mesa("M01",4,true));cardapio.salvar(new ItemCardapio("PR01","Risoto de cogumelos",new BigDecimal("48.00"),20,true));
  var reservaService=new ReservaService(mesas,reservas,Clock.systemDefaultZone());var r=reservaService.criar("CLI-01","M01",4,LocalDateTime.now().plusHours(2));reservaService.confirmar(r.id());
  var pedidoService=new PedidoService(cardapio,pedidos,Clock.systemDefaultZone());var p=pedidoService.criar("CLI-01","M01",List.of(new PedidoService.SolicitacaoItem("PR01",2)),"ALMOCO10");
  pedidoService.enviarCozinha(p.id());pedidoService.iniciarPreparo(p.id());pedidoService.marcarPronto(p.id());pedidoService.pagar(p.id(),p.total());
  System.out.printf("Reserva %s | Pedido %s | status=%s | total=R$ %s%n",r.status(),p.id(),p.status(),p.total());
 }
}
