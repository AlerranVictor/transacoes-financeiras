package com.financas.transacoes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.transacoes.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Override
    boolean existsById(Integer id);
}
