package com.financas.transacoes.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.Transacoes;
import com.financas.transacoes.domain.repository.TransacaoRepository;
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
    public List<Transacao> findDespesas(){
        return transacaoRepository.findByTipo("DESPESA")
        .stream()
        .collect(Collectors.toList());
    }
    @Override
    public List<Transacao> findReceitas(){
        return transacaoRepository.findByTipo("RECEITA")
        .stream()
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
            transacao.setData(transacaoToUpdate.getData());
            transacao.setValor(transacaoToUpdate.getValor());

            return transacaoRepository.save(transacao);
        });
        
    }

    @Override
    public Transacoes obterTransacoesSeparadas() {
        List<Transacao> todasTransacoes = transacaoRepository.findAll();

        List<Transacao> receitas = todasTransacoes.stream()
                .filter(transacao -> "RECEITA".equals(transacao.getTipo()))
                .collect(Collectors.toList());

        List<Transacao> despesas = todasTransacoes.stream()
                .filter(transacao -> "DESPESA".equals(transacao.getTipo()))
                .collect(Collectors.toList());

        BigDecimal total = todasTransacoes.stream()
        .map(transacao -> transacao.getValor())
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Transacoes(receitas, despesas, total);
    }

}
