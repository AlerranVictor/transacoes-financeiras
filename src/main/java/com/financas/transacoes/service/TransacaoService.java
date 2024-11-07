package com.financas.transacoes.service;

import java.util.List;
import java.util.Optional;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.dto.TransacaoDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;

public interface TransacaoService {
    Transacao findById(Integer id);
    List<TransacaoDTO> findDespesas();
    List<TransacaoDTO> findReceitas();
    Transacao create(Transacao transacaoToCreate);
    void delete(Integer id);
    Optional<Transacao> update(Integer id, Transacao transacaoToUpdate);
    TransacaoResponseDTO obterTransacoesSeparadas();
}
