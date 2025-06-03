package com.financas.transacoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TransacaoResponseDTO {
    private String tipo;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;
    private UUID uuid;

    public TransacaoResponseDTO(String tipo, String descricao, LocalDate data, BigDecimal valor, UUID uuid){
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.uuid = uuid;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
