package com.financas.transacoes.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.dto.AnoEMesDTO;
import com.financas.transacoes.dto.AnoEMesRequestDTO;
import com.financas.transacoes.dto.TransacaoRequestDTO;
import com.financas.transacoes.dto.TransacaoResponseDTO;
import com.financas.transacoes.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> findByDate(@RequestBody AnoEMesRequestDTO data, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(transacaoService.findByDate(data, user.getId()));
    }

    @GetMapping("/datas")
    public ResponseEntity<AnoEMesDTO> findUsedDates(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(transacaoService.findUsedDates(user.getId()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TransacaoRequestDTO transacao, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        transacaoService.create(transacao, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<Void> update(@PathVariable UUID uuid, @RequestBody Authentication authentication, TransacaoRequestDTO transacao) {
        User user = (User) authentication.getPrincipal();
        transacaoService.update(uuid, user.getId(), transacao);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid, @RequestBody Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        transacaoService.delete(uuid, user.getId());
        return ResponseEntity.noContent().build();
    }
}
