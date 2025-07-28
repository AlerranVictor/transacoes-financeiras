package com.financas.transacoes.dto;

import java.util.List;

public class AnoEMesDTO {

    public AnoEMesDTO(List<Integer> ano, List<Integer> mes) {
        this.ano = ano;
        this.mes = mes;
    }

    private List<Integer> ano;
    private List<Integer> mes;
    
    public List<Integer> getAno() {
        return ano;
    }
    public void setAno(List<Integer> ano) {
        this.ano = ano;
    }
    public List<Integer> getMes() {
        return mes;
    }
    public void setMes(List<Integer> mes) {
        this.mes = mes;
    }
}
