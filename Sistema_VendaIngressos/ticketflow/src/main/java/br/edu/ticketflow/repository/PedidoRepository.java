package br.edu.ticketflow.repository;
import br.edu.ticketflow.domain.Pedido;
import java.util.*;
public interface PedidoRepository { Optional<Pedido> buscarPorId(String id); void salvar(Pedido pedido); List<Pedido> listar(); }
