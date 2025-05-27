package com.seuprojeto;

public class Credenciais {
    private String email;
    private String senha;

    public Credenciais(String nome, String senha){
        this.email = nome;
        this.senha = senha;
    }

    public String getNome() {
        return email;
    }
    public String getSenha() {
        return senha;
    }

}
