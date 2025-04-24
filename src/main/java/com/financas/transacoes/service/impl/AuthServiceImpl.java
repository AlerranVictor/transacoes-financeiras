package com.financas.transacoes.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financas.transacoes.domain.model.User;
import com.financas.transacoes.domain.repository.UserRepository;
import com.financas.transacoes.dto.LoginRequestDTO;
import com.financas.transacoes.dto.LoginResponseDTO;
import com.financas.transacoes.dto.RegisterRequestDTO;
import com.financas.transacoes.dto.RegisterResponseDTO;
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
    public RegisterResponseDTO criarUsuario(RegisterRequestDTO usuario) {
        User newUser = new User();
        newUser.setSenha(passwordEncoder.encode(usuario.getSenha()));
        newUser.setNome(usuario.getNome());
        newUser.setSobrenome(usuario.getSobrenome());
        newUser.setEmail(usuario.getEmail());
        System.out.println("Cadastrando usuário: " + usuario.getEmail() + " - " + usuario.getSenha());
        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return new RegisterResponseDTO(newUser.getNome(), newUser.getId(), token);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = this.userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        if (passwordEncoder.matches(loginRequest.getSenha(), user.getSenha())) {
            System.out.println("Usuário existente");
            String token = this.tokenService.generateToken(user);
            System.out.println("Token gerado");
            return new LoginResponseDTO(user.getNome(), token, user.getId());
        }
        throw new RuntimeException("Senha inválida");
    }

}
