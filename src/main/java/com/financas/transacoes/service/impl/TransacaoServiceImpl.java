package com.financas.transacoes.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.repository.TransacaoRepository;
import com.financas.transacoes.dto.TransacaoDTO;
import com.financas.transacoes.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }


    @Override
    public Transacao findById(Integer id) {
        return transacaoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<TransacaoDTO> findAll(){
        return transacaoRepository.findAll().stream()
        .map(transacao -> new TransacaoDTO(
            transacao.getId(), 
            transacao.getTipo(), 
            transacao.getCategoria(), 
            transacao.getValor()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public Transacao create(Transacao transacaoToCreate) {
        return transacaoRepository.save(transacaoToCreate);
    }

    @Override
    public void delete(Integer id) {
        transacaoRepository.deleteById(id);
    }

    @Override
    public Transacao update(Integer id, Transacao transacaoToUpdate) {
        return transacaoRepository.findById(id).map(transacao -> {
            transacao.setTipo(transacaoToUpdate.getTipo());
            transacao.setCategoria(transacaoToUpdate.getCategoria());
            transacao.setValor(transacaoToUpdate.getValor());

            return transacaoRepository.save(transacao);
        })
        .orElseThrow(() -> new ResourceAccessException("Transação não encontrada"));
    }

}
