package com.financas.transacoes.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.domain.repository.TransacaoRepository;
import com.financas.transacoes.domain.repository.UserRepository;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;
import com.financas.transacoes.dto.TransacoesResponseDTO;
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
    public Transacao create(TransacaoRequestDTO transacaoToCreate) {
        User usuario = userRepository.findById(transacaoToCreate.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        transacaoToCreate.setTipo(transacaoToCreate.getTipo().toUpperCase());
        Transacao transacao = new Transacao();
        transacao.setTipo(transacaoToCreate.getTipo());
        transacao.setCategoria(transacaoToCreate.getCategoria());
        transacao.setData(transacaoToCreate.getData());
        transacao.setValor(transacaoToCreate.getValor());
        transacao.setUsuario(usuario);
        return transacaoRepository.save(transacao);
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
    public TransacoesResponseDTO obterTransacoesSeparadas(Integer usuarioId) {
        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        List<Transacao> todasTransacoesPorUser = usuario.getTransacoes();

        List<TransacaoResponseDTO> receitas = todasTransacoesPorUser.stream()
                .filter(transacao -> "RECEITA".equals(transacao.getTipo()))
                .map(transacao -> new TransacaoResponseDTO(
                    transacao.getTipo(),
                    transacao.getCategoria(),
                    transacao.getData(),
                    transacao.getValor(),
                    transacao.getId(),
                    transacao.getUsuario().getId()
                ))
                .collect(Collectors.toList());

        List<TransacaoResponseDTO> despesas = todasTransacoesPorUser.stream()
                .filter(transacao -> "DESPESA".equals(transacao.getTipo()))
                .map(transacao -> new TransacaoResponseDTO(
                    transacao.getTipo(), 
                    transacao.getCategoria(), 
                    transacao.getData(), 
                    transacao.getValor(),
                    transacao.getId(),
                    transacao.getUsuario().getId()
                ))
                .collect(Collectors.toList());

        BigDecimal total = todasTransacoesPorUser.stream()
        .map(transacao -> transacao.getValor())
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TransacoesResponseDTO(receitas, despesas, total);
    }

}
