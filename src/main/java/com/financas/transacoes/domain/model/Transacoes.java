package com.financas.transacoes.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Transacoes {
    private List<Transacao> receitas;
    private List<Transacao> despesas;
    private BigDecimal total;

    public Transacoes(List<Transacao> receitas, List<Transacao> despesas, BigDecimal total){
        this.receitas = receitas;
        this.despesas = despesas;
        this.total = total;
    }

    public List<Transacao> getReceitas() {
        return receitas;
    }
    public void setReceitas(List<Transacao> receitas) {
        this.receitas = receitas;
    }
    public List<Transacao> getDespesas() {
        return despesas;
    }
    public void setDespesas(List<Transacao> despesas) {
        this.despesas = despesas;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
