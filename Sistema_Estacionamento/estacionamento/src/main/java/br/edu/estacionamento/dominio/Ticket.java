package br.edu.estacionamento.dominio;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Ticket {
    private final UUID id;
    private final String placa;
    private final TipoVeiculo tipoVeiculo;
    private final String codigoVaga;
    private final ZonedDateTime entrada;
    private final boolean mensalista;
    private EstadoTicket estado = EstadoTicket.ABERTO;
    private BigDecimal valorPago;
    private ZonedDateTime pagoEm;
    private ZonedDateTime saida;

    public Ticket(String placa, TipoVeiculo tipoVeiculo, String codigoVaga, ZonedDateTime entrada, boolean mensalista) {
        this.id=UUID.randomUUID(); this.placa=Objects.requireNonNull(placa); this.tipoVeiculo=Objects.requireNonNull(tipoVeiculo);
        this.codigoVaga=Objects.requireNonNull(codigoVaga); this.entrada=Objects.requireNonNull(entrada); this.mensalista=mensalista;
    }
    public UUID id(){return id;} public String placa(){return placa;} public TipoVeiculo tipoVeiculo(){return tipoVeiculo;}
    public String codigoVaga(){return codigoVaga;} public ZonedDateTime entrada(){return entrada;} public boolean mensalista(){return mensalista;}
    public EstadoTicket estado(){return estado;} public BigDecimal valorPago(){return valorPago;} public ZonedDateTime pagoEm(){return pagoEm;} public ZonedDateTime saida(){return saida;}
    public void pagar(BigDecimal valor, ZonedDateTime instante){valorPago=valor; pagoEm=instante; estado=EstadoTicket.PAGO;}
    public void reabrir(){estado=EstadoTicket.ABERTO;}
    public void encerrar(ZonedDateTime instante){saida=instante; estado=EstadoTicket.ENCERRADO;}
    public void cancelar(){estado=EstadoTicket.CANCELADO;}
}
