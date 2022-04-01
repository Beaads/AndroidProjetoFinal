package com.beatriz.projetofinalandroid.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    @NotNull
    private int id;
    private String usuario;
    private String senha;

    public Usuario(int id, String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getIdUsuario() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
