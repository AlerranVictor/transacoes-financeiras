package com.financas.transacoes.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class TransacaoDTO {
    private Integer id;
    private String tipo;
    private String categoria;
    private String valorFormatado;

    public TransacaoDTO(Integer id, String tipo, String categoria, BigDecimal valor) {
        this.id = id;
        this.tipo = tipo;
        this.categoria = categoria;
        this.valorFormatado = formatarParaMoeda(valor);
    }

    private String formatarParaMoeda(BigDecimal valor){
        NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatador.format(valor);
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getValorFormatado() {
        return valorFormatado;
    }
    
}
