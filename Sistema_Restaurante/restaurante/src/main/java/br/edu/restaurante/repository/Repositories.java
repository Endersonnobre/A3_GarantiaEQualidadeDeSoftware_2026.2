package br.edu.restaurante.repository;
import br.edu.restaurante.domain.*;import java.util.*;
public final class Repositories {
 private Repositories(){}
 public interface Mesas { Optional<Mesa> porId(String id); void salvar(Mesa m); }
 public interface Reservas { Optional<Reserva> porId(String id); List<Reserva> listar(); void salvar(Reserva r); }
 public interface Cardapio { Optional<ItemCardapio> porId(String id); void salvar(ItemCardapio i); }
 public interface Pedidos { Optional<Pedido> porId(String id); List<Pedido> listar(); void salvar(Pedido p); }
}
