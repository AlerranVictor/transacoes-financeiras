package com.financas.transacoes.dto;

public class RegisterResponseDTO {

    private String name;
    private Integer usuarioId;
    private String token;

    public RegisterResponseDTO(String name, Integer usuarioId, String token){
        this.name = name;
        this.usuarioId = usuarioId;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
