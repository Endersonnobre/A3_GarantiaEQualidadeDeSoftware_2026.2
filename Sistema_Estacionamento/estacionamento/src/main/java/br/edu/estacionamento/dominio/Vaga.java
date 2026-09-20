package br.edu.estacionamento.dominio;

import java.util.Objects;

public final class Vaga {
    private final String codigo;
    private final TipoVaga tipo;
    private EstadoVaga estado;

    public Vaga(String codigo, TipoVaga tipo) {
        if (codigo == null || codigo.isBlank() || codigo.trim().length() > 12) throw new IllegalArgumentException("Código inválido");
        this.codigo = codigo.trim().toUpperCase();
        this.tipo = Objects.requireNonNull(tipo);
        this.estado = EstadoVaga.LIVRE;
    }
    public String codigo() { return codigo; }
    public TipoVaga tipo() { return tipo; }
    public EstadoVaga estado() { return estado; }
    public void alterarEstado(EstadoVaga estado) { this.estado = Objects.requireNonNull(estado); }
}
