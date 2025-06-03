package com.financas.transacoes.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.domain.repository.UserRepository;
import com.financas.transacoes.dto.AuthResponseDTO;
import com.financas.transacoes.dto.LoginRequestDTO;
import com.financas.transacoes.dto.RegisterRequestDTO;
import com.financas.transacoes.security.TokenService;
import com.financas.transacoes.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AuthResponseDTO criarUsuario(RegisterRequestDTO usuario) {
        User newUser = new User();
        newUser.setSenha(passwordEncoder.encode(usuario.getSenha()));
        newUser.setNome(usuario.getNome());
        newUser.setSobrenome(usuario.getSobrenome());
        newUser.setEmail(usuario.getEmail());
        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return new AuthResponseDTO(newUser.getNome(), token);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        User user = this.userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        if (passwordEncoder.matches(loginRequest.getSenha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return new AuthResponseDTO(user.getNome(), token);
        }
        throw new RuntimeException("Senha inválida");
    }

}
