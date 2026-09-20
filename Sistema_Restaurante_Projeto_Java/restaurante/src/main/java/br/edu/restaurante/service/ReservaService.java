package br.edu.restaurante.service;
import br.edu.restaurante.domain.*;import br.edu.restaurante.exception.RegraNegocioException;import br.edu.restaurante.repository.Repositories;
import java.time.*;import java.util.UUID;
public final class ReservaService {
 private final Repositories.Mesas mesas;private final Repositories.Reservas reservas;private final Clock relogio;
 public ReservaService(Repositories.Mesas mesas,Repositories.Reservas reservas,Clock relogio){this.mesas=mesas;this.reservas=reservas;this.relogio=relogio;}
 public Reserva criar(String clienteId,String mesaId,int pessoas,LocalDateTime horario){
  Mesa mesa=mesas.porId(mesaId).orElseThrow(()->new RegraNegocioException("Mesa não encontrada"));
  if(!mesa.ativa())throw new RegraNegocioException("Mesa inativa");
  if(pessoas<1||pessoas>mesa.capacidade())throw new RegraNegocioException("Quantidade de pessoas inválida");
  if(horario.isBefore(LocalDateTime.now(relogio).plusMinutes(30)))throw new RegraNegocioException("Antecedência mínima não atendida");
  for(Reserva r:reservas.listar())if(r.mesaId().equals(mesaId)&&r.status()==StatusReserva.CONFIRMADA&&Math.abs(Duration.between(r.horario(),horario).toMinutes())<120)throw new RegraNegocioException("Mesa indisponível");
  Reserva r=new Reserva(UUID.randomUUID().toString(),clienteId,mesaId,pessoas,horario,LocalDateTime.now(relogio));reservas.salvar(r);return r;
 }
 public void confirmar(String id){Reserva r=obter(id);r.confirmar();reservas.salvar(r);}
 public int expirarPendentes(){LocalDateTime limite=LocalDateTime.now(relogio).minusMinutes(10);int n=0;for(Reserva r:reservas.listar())if(r.status()==StatusReserva.PENDENTE&&r.criadaEm().isBefore(limite)){r.cancelar();n++;}return n;}
 private Reserva obter(String id){return reservas.porId(id).orElseThrow(()->new RegraNegocioException("Reserva não encontrada"));}
}
