package com.financas.transacoes.service;

import java.util.List;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.dto.TransacaoDTO;

public interface TransacaoService {
    Transacao findById(Integer id);
    List<TransacaoDTO> findAll();
    List<TransacaoDTO> findDespesas();
    List<TransacaoDTO> findReceitas();
    Transacao create(Transacao transacaoToCreate);
    void delete(Integer id);
    Transacao update(Integer id, Transacao transacaoToUpdate);
}
