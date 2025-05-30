package com.financas.transacoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.transacoes.domain.model.Transacao;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacoesResponseDTO;
import com.financas.transacoes.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<TransacoesResponseDTO> obterTransacoesSeparadas(@RequestBody Integer usuarioId){
        TransacoesResponseDTO tSeparadas = transacaoService.obterTransacoesSeparadas(usuarioId);
        return ResponseEntity.ok(tSeparadas);
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<Transacao>> getDespesas(){
        List<Transacao> despesas = transacaoService.findDespesas();
        return ResponseEntity.ok(despesas);
    }

    @GetMapping("/receitas")
    public ResponseEntity<List<Transacao>> getReceitas(){
        List<Transacao> receitas = transacaoService.findReceitas();
        return ResponseEntity.ok(receitas);
    }

    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody TransacaoRequestDTO transacao){
        Transacao novaTransacao = transacaoService.create(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Transacao>> update(@PathVariable Integer id, @RequestBody Transacao transacao){
        Optional<Transacao> attTransacao = transacaoService.update(id, transacao);
        return ResponseEntity.ok(attTransacao);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        transacaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
