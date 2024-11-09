package com.financas.transacoes.service;

import java.util.List;
import java.util.Optional;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.Transacoes;

public interface TransacaoService {
    Transacao findById(Integer id);
    List<Transacao> findDespesas();
    List<Transacao> findReceitas();
    Transacao create(Transacao transacaoToCreate);
    void delete(Integer id);
    Optional<Transacao> update(Integer id, Transacao transacaoToUpdate);
    Transacoes obterTransacoesSeparadas();
}
