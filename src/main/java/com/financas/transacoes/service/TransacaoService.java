package com.financas.transacoes.service;

import java.util.List;
import java.util.Optional;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacoesResponseDTO;

public interface TransacaoService {
    Transacao findById(Integer id);
    List<Transacao> findDespesas();
    List<Transacao> findReceitas();
    Transacao create(TransacaoRequestDTO transacaoToCreate);
    void delete(Integer id);
    Optional<Transacao> update(Integer id, Transacao transacaoToUpdate);
    TransacoesResponseDTO obterTransacoesSeparadas(Integer usuarioId);
}
