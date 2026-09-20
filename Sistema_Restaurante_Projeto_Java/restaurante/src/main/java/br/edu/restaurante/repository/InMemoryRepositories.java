package br.edu.restaurante.repository;
import br.edu.restaurante.domain.*;import java.util.*;
public final class InMemoryRepositories {
 private InMemoryRepositories(){}
 public static final class Mesas implements Repositories.Mesas {private final Map<String,Mesa>d=new HashMap<>();
 @Override
 public Optional<Mesa>porId(String id){return Optional.ofNullable(d.get(id));}
 @Override
 public void salvar(Mesa m){d.put(m.id(),m);}}
 public static final class Reservas implements Repositories.Reservas {private final Map<String,Reserva>d=new LinkedHashMap<>();
 @Override
 public Optional<Reserva>porId(String id){return Optional.ofNullable(d.get(id));}
 @Override
 public List<Reserva>listar(){return List.copyOf(d.values());}
 @Override
 public void salvar(Reserva r){d.put(r.id(),r);}}
 public static final class Cardapio implements Repositories.Cardapio {private final Map<String,ItemCardapio>d=new HashMap<>();
 @Override
 public Optional<ItemCardapio>porId(String id){return Optional.ofNullable(d.get(id));}
 @Override
 public void salvar(ItemCardapio i){d.put(i.id(),i);}}
 public static final class Pedidos implements Repositories.Pedidos {private final Map<String,Pedido>d=new LinkedHashMap<>();
 @Override
 public Optional<Pedido>porId(String id){return Optional.ofNullable(d.get(id));}
 @Override
 public List<Pedido>listar(){return List.copyOf(d.values());}
 @Override
 public void salvar(Pedido p){d.put(p.id(),p);}}
}
