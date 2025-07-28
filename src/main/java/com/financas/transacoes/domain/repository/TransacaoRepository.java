package com.financas.transacoes.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.financas.transacoes.domain.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Override
    boolean existsById(Integer id);

    Optional<Transacao> findByUuidAndUsuarioId(UUID uuid, Integer usuarioId);

    @Query(
            value = """
                    SELECT * FROM (
                    SELECT * FROM (
                    SELECT * FROM tb_transacao WHERE usuario_id = :usuarioId
                    ) WHERE EXTRACT(YEAR FROM data) = :year
                    ) WHERE EXTRACT(MONTH FROM data) = :month
                    """,
            nativeQuery = true
    )
    List<Transacao> findByYearMonth(@Param("usuarioId") Integer usuarioId, @Param("year") int year, @Param("month") int month);

    @Query(
            value = """
                    SELECT DISTINCT
                        EXTRACT(YEAR FROM m.data) AS ano
                    FROM tb_transacao m
                    WHERE m.usuario_id = :usuarioId
                    ORDER BY ano DESC
                    """,
            nativeQuery = true
    )
    List<Integer> findUsedYears(@Param("usuarioId") Integer usuarioId);

    @Query(
        value = """
                SELECT DISTINCT
                        EXTRACT(MONTH FROM m.data) AS mes
                FROM tb_transacao m
                WHERE m.usuario_id = :usuarioId
                ORDER BY mes DESC
                """,
        nativeQuery = true
    )
    List<Integer> findUsedMonths(@Param("usuarioId") Integer usuarioId);
}
