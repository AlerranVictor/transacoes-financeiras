package com.financas.transacoes.service;

import java.util.Optional;

import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.dto.LoginRequestDTO;
import com.financas.transacoes.dto.LoginResponseDTO;
import com.financas.transacoes.dto.RegisterRequestDTO;
import com.financas.transacoes.dto.RegisterResponseDTO;

public interface AuthService {

    Optional<User> findByEmail(String email);
    RegisterResponseDTO criarUsuario(RegisterRequestDTO usuario);
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
