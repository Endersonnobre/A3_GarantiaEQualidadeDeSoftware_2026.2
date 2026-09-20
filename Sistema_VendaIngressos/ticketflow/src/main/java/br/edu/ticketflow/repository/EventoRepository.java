package br.edu.ticketflow.repository;
import br.edu.ticketflow.domain.Evento;
import java.util.Optional;
public interface EventoRepository { Optional<Evento> buscarPorId(String id); void salvar(Evento evento); }
