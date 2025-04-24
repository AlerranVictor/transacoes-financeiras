package com.financas.transacoes.dto;

import java.math.BigDecimal;
import java.util.List;

public class TransacoesResponseDTO {

    private List<TransacaoResponseDTO> receitas;
    private List<TransacaoResponseDTO> despesas;
    private BigDecimal total;

    public TransacoesResponseDTO (List<TransacaoResponseDTO> receitas, List<TransacaoResponseDTO> despesas, BigDecimal total) {
        this.receitas = receitas;
        this.despesas = despesas;
        this.total = total;
    }

    public List<TransacaoResponseDTO> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<TransacaoResponseDTO> receitas) {
        this.receitas = receitas;
    }

    public List<TransacaoResponseDTO> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<TransacaoResponseDTO> despesas) {
        this.despesas = despesas;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
