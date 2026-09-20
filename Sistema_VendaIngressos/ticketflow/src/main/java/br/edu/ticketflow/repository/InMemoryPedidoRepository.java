package br.edu.ticketflow.repository;
import br.edu.ticketflow.domain.Pedido;
import java.util.*;
public final class InMemoryPedidoRepository implements PedidoRepository {
  private final Map<String,Pedido> dados=new LinkedHashMap<>();
  @Override
  public Optional<Pedido> buscarPorId(String id){return Optional.ofNullable(dados.get(id));}
  @Override
  public void salvar(Pedido pedido){dados.put(pedido.id(),pedido);}
  @Override
  public List<Pedido> listar(){return List.copyOf(dados.values());}
}
