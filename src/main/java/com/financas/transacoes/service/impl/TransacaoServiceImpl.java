package com.financas.transacoes.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.domain.repository.TransacaoRepository;
import com.financas.transacoes.domain.repository.UserRepository;
import com.financas.transacoes.dto.AnoEMesRequestDTO;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;
import com.financas.transacoes.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final UserRepository userRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository, UserRepository userRepository) {
        this.transacaoRepository = transacaoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Transacao findById(Integer id) {
        return transacaoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void create(TransacaoRequestDTO transacaoToCreate, User user) {
        if(transacaoToCreate.getTipo().equalsIgnoreCase("receita") | transacaoToCreate.getTipo().equalsIgnoreCase("despesa")){
            Transacao transacao = new Transacao();
            transacao.setTipo(transacaoToCreate.getTipo());
            transacao.setDescricao(transacaoToCreate.getDescricao());
            transacao.setData(transacaoToCreate.getData());
            if(transacaoToCreate.getTipo().equalsIgnoreCase("despesa") && transacaoToCreate.getValor().compareTo(java.math.BigDecimal.ZERO) > 0){
                transacao.setValor(transacaoToCreate.getValor().negate());
                //user.setSaldo(user.getSaldo().add(transacaoToCreate.getValor()));
            } else if(transacaoToCreate.getTipo().equalsIgnoreCase("receita") && transacaoToCreate.getValor().compareTo(java.math.BigDecimal.ZERO) < 0){
                transacao.setValor(transacaoToCreate.getValor().abs());
                //user.setSaldo(user.getSaldo().add(transacaoToCreate.getValor()));
            } else {
                transacao.setValor(transacaoToCreate.getValor());
            }
                user.setSaldo(user.getSaldo().add(transacaoToCreate.getValor()));

            transacao.setUsuario(user);
            
            userRepository.save(user);
            transacaoRepository.save(transacao);
        } else {
            throw new RuntimeException("Tipo inválido");
        }
    }

    @Override
    public void delete(UUID uuid, Long usuarioId) {
        Transacao transacao = transacaoRepository.findByUuidAndUsuarioId(uuid, usuarioId).orElseThrow(NoSuchElementException::new);
        transacaoRepository.delete(transacao);
    }

    @Override
    public void update(UUID uuid, Long usuarioId, TransacaoRequestDTO transacaoToUpdate) {
        Transacao transacao = transacaoRepository.findByUuidAndUsuarioId(uuid, usuarioId).orElseThrow(NoSuchElementException::new);
            transacao.setTipo(transacaoToUpdate.getTipo().toUpperCase());
            transacao.setDescricao(transacaoToUpdate.getDescricao());
            transacao.setData(transacaoToUpdate.getData());
            transacao.setValor(transacaoToUpdate.getValor());

            transacaoRepository.save(transacao);
    }

    @Override
    public List<TransacaoResponseDTO> findByDate(AnoEMesRequestDTO data, Long usuarioId) {
        List<Transacao> transacoesPorUser = transacaoRepository.findByYearMonth(usuarioId, data.getAno(), data.getMes());
        return transacoesPorUser.stream()
            .map(transacao -> new TransacaoResponseDTO(
                transacao.getTipo(),
                transacao.getDescricao(),
                transacao.getData(),
                transacao.getValor(),
                transacao.getUuid()
                )).collect(Collectors.toList());
    }

@Override
    public List<Object []> findUsedDates(Long usuarioId) {
        return transacaoRepository.findUsedDates(usuarioId);
    }
}