package br.edu.estacionamento.repositorio;

import br.edu.estacionamento.dominio.*;
import java.util.*;

public final class BancoEmMemoria {
 public final Map<String,Vaga> vagas=new LinkedHashMap<>();
 public final Map<UUID,Ticket> tickets=new LinkedHashMap<>();
 public final Map<String,Pagamento> pagamentosPorChave=new HashMap<>();
 public final Map<String,Mensalista> mensalistas=new HashMap<>();
 public final Map<UUID,Reserva> reservas=new LinkedHashMap<>();
 public void limpar(){vagas.clear();tickets.clear();pagamentosPorChave.clear();mensalistas.clear();reservas.clear();}
}
