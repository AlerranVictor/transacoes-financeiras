package com.financas.transacoes.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.dto.AuthResponseDTO;
import com.financas.transacoes.dto.LoginRequestDTO;
import com.financas.transacoes.dto.RegisterRequestDTO;
import com.financas.transacoes.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = authService.findByEmail(body.getEmail());
        if(user.isPresent()){
            System.out.println("Usuário já cadastrado - " + body.getEmail());
            return ResponseEntity.badRequest().body("Usuário já cadastrado");
        }
        System.out.print("Cadastrando novo usuário - " + body.getEmail());
        
        return ResponseEntity.ok(authService.criarUsuario(body));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        return ResponseEntity.ok(authService.login(body));
    }
}
