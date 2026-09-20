package br.edu.estoque.repositorio;
import br.edu.estoque.dominio.*;import java.util.*;
public final class BancoEmMemoria {
 public final Map<String,Produto> produtos=new LinkedHashMap<>();public final Map<String,Deposito> depositos=new LinkedHashMap<>();public final Map<UUID,Lote> lotes=new LinkedHashMap<>();public final Map<UUID,Reserva> reservas=new LinkedHashMap<>();public final Map<String,Transferencia> transferencias=new HashMap<>();
 public void limpar(){produtos.clear();depositos.clear();lotes.clear();reservas.clear();transferencias.clear();}
}
