package com.seuprojeto;

import com.seuprojeto.crypto;
import com.seuprojeto.Credenciais;

import java.util.ArrayList;
import java.util.List;

public class Gerenciar {
    private List<Credenciais> credenciais = new ArrayList<>();

    public void salvarCredenciais(Credenciais credenciais) {
        this.credenciais.add(credenciais);
    }

    public void listarCredenciais(){
        if(credenciais.isEmpty()){
            System.out.println("Nenhuma credencial encontrada");
            return;
        }
        for (Credenciais cred : credenciais) {
            System.out.println("Email: "+cred.getEmail());
            System.out.println("Senha: "+crypto.decrypt(cred.getSenha()));
            System.out.println("----------------------------------------");
        }
    }
}
