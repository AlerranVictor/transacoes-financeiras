package com.financas.transacoes.dto;

public class AnoEMesRequestDTO {
    int ano;
    int mes;
    
    public AnoEMesRequestDTO() {}

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
}
