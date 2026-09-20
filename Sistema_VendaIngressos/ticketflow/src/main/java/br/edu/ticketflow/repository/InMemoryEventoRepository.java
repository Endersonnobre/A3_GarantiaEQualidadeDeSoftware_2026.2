package br.edu.ticketflow.repository;
import br.edu.ticketflow.domain.Evento;
import java.util.*;
public final class InMemoryEventoRepository implements EventoRepository {
  private final Map<String,Evento> dados=new HashMap<>();
  @Override
  public Optional<Evento> buscarPorId(String id){return Optional.ofNullable(dados.get(id));}
  @Override
  public void salvar(Evento evento){dados.put(evento.id(),evento);}
}
