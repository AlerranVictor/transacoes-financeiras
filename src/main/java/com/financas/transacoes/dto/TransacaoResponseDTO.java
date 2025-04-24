package com.financas.transacoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacaoResponseDTO {

    private String tipo;
    private String categoria;
    private LocalDate data;
    private BigDecimal valor;
    private Integer id;
    private Integer usuarioId;

    public TransacaoResponseDTO(String tipo, String categoria, LocalDate data, BigDecimal valor, Integer id, Integer usuarioId){
        this.tipo = tipo;
        this.categoria = categoria;
        this.data = data;
        this.valor = valor;
        this.id = id;
        this.usuarioId = usuarioId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
