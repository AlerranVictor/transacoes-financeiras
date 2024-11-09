package com.financas.transacoes.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.repository.TransacaoRepository;
import com.financas.transacoes.dto.TransacaoDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;
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
    public List<TransacaoDTO> findDespesas(){
        return transacaoRepository.findByTipo("DESPESA")
        .stream()
        .map( transacao -> new TransacaoDTO(
            transacao.getId(),
            transacao.getTipo(),
            transacao.getCategoria(),
            transacao.getValor()
        ))
        .collect(Collectors.toList());
    }
    @Override
    public List<TransacaoDTO> findReceitas(){
        return transacaoRepository.findByTipo("RECEITA")
        .stream()
        .map( transacao -> new TransacaoDTO(
            transacao.getId(),
            transacao.getTipo(),
            transacao.getCategoria(),
            transacao.getValor()
        ))
        .collect(Collectors.toList());
    }

    @Override
    public Transacao create(Transacao transacaoToCreate) {
        transacaoToCreate.setTipo(transacaoToCreate.getTipo().toUpperCase());
        return transacaoRepository.save(transacaoToCreate);
    }

    @Override
    public void delete(Integer id) {
        transacaoRepository.deleteById(id);
    }

    @Override
    public Optional<Transacao> update(Integer id, Transacao transacaoToUpdate) {
        return transacaoRepository.findById(id).map(transacao -> {
            transacao.setTipo(transacaoToUpdate.getTipo().toUpperCase());
            transacao.setCategoria(transacaoToUpdate.getCategoria());
            transacao.setValor(transacaoToUpdate.getValor());

            return transacaoRepository.save(transacao);
        });
        
    }

    @Override
    public TransacaoResponseDTO obterTransacoesSeparadas() {
        List<Transacao> todasTransacoes = transacaoRepository.findAll();

        List<TransacaoDTO> receitas = todasTransacoes.stream()
                .filter(transacao -> "RECEITA".equals(transacao.getTipo()))
                .map(transacao -> new TransacaoDTO(transacao.getId(), transacao.getTipo(), transacao.getCategoria(),
                        transacao.getValor()))
                .collect(Collectors.toList());

        List<TransacaoDTO> despesas = todasTransacoes.stream()
                .filter(transacao -> "DESPESA".equals(transacao.getTipo()))
                .map(transacao -> new TransacaoDTO(transacao.getId(), transacao.getTipo(), transacao.getCategoria(),
                        transacao.getValor()))
                .collect(Collectors.toList());

        BigDecimal total = todasTransacoes.stream()
        .map(transacao -> transacao.getValor())
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TransacaoResponseDTO(receitas, despesas, total);
    }

}
