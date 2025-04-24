package com.financas.transacoes.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.domain.model.User;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Override
    boolean existsById(Integer id);

    List<Transacao> findByTipo(String tipo);

    List<Transacao> findByUsuario(User usuario);
}
