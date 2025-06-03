package com.financas.transacoes.service;

import java.util.Optional;

import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.dto.AuthResponseDTO;
import com.financas.transacoes.dto.LoginRequestDTO;
import com.financas.transacoes.dto.RegisterRequestDTO;

public interface AuthService {

    Optional<User> findByEmail(String email);
    AuthResponseDTO criarUsuario(RegisterRequestDTO usuario);
    AuthResponseDTO login(LoginRequestDTO loginRequest);
}
