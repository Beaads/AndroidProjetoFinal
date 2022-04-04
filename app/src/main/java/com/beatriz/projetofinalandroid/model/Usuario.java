package com.beatriz.projetofinalandroid.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    @NotNull
    private int id;
    private String usuario;
    private String senha;

    public Usuario() {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
