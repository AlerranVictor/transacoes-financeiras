package com.financas.transacoes.service;

import java.util.List;
import java.util.UUID;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.dto.AnoEMesRequestDTO;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;

public interface TransacaoService {
    Transacao findById(Integer id);
    List<TransacaoResponseDTO> findByDate(AnoEMesRequestDTO data, Long usuarioId);
    List<Object []> findUsedDates(Long usuarioId);
    void create(TransacaoRequestDTO transacaoToCreate, User user);
    void delete(UUID uuid, Long usuarioId);
    void update(UUID uuid, Long usuarioId, TransacaoRequestDTO transacaoToUpdate);
}
