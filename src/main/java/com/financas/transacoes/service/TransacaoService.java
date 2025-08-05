package com.financas.transacoes.service;

import java.util.List;
import java.util.UUID;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.dto.AnoEMesDTO;
import com.financas.transacoes.dto.AnoEMesRequestDTO;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;

public interface TransacaoService {
    Transacao findById(Integer id);
    List<TransacaoResponseDTO> findByDate(AnoEMesRequestDTO data, Integer usuarioId);
    List<Object []> findUsedDates(Integer usuarioId);
    void create(TransacaoRequestDTO transacaoToCreate, User user);
    void delete(UUID uuid, Integer usuarioId);
    void update(UUID uuid, Integer usuarioId, TransacaoRequestDTO transacaoToUpdate);
}
