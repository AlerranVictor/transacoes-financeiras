package com.financas.transacoes.dto;

import java.util.List;

public class TransacaoResponseDTO {
    private List<TransacaoDTO> receitas;
    private List<TransacaoDTO> despesas;

    public TransacaoResponseDTO (List<TransacaoDTO> receitas, List<TransacaoDTO> despesas) {
        this.receitas = receitas;
        this.despesas = despesas;
    }

    public List<TransacaoDTO> getReceitas() {
        return receitas;
    }

    public List<TransacaoDTO> getDespesas() {
        return despesas;
    }
}
