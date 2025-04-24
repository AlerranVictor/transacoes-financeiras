package com.financas.transacoes.dto;

public class LoginResponseDTO {
    private String name;
    private String token;
    private Integer usuarioId;

    public LoginResponseDTO(String name, String token, Integer usuarioId){
        this.name = name;
        this.token = token;
        this.usuarioId = usuarioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

}
