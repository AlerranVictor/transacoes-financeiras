package com.financas.transacoes.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransacaoResponseDTO {
    private List<TransacaoDTO> receitas;
    private List<TransacaoDTO> despesas;
    private String total;

    public TransacaoResponseDTO (List<TransacaoDTO> receitas, List<TransacaoDTO> despesas, BigDecimal total) {
        this.receitas = receitas;
        this.despesas = despesas;
        this.total = formatarParaMoeda(total);
    }

    private String formatarParaMoeda(BigDecimal valor){
        NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatador.format(valor);
    }

    public List<TransacaoDTO> getReceitas() {
        return receitas;
    }

    public List<TransacaoDTO> getDespesas() {
        return despesas;
    }

    public String getTotal() {
        return total;
    }

}
