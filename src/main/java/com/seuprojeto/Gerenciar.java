package com.seuprojeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerenciar {
    private List<Credenciais> credenciais;

    public Gerenciar() {
        this.credenciais = new ArrayList<>();
    }

    public void salvarCredenciais(Credenciais credencial) {
        credenciais.add(credencial);
    }

    public void listarCredenciais() {
        if (credenciais.isEmpty()) {
            System.out.println("Nenhuma credencial encontrada");
            return;
        }
        for (Credenciais cred : credenciais) {
            System.out.println("Email: " + cred.getEmail());
            System.out.println("Senha: " + Crypto.decrypt(cred.getSenha()));
            System.out.println("----------------------------------------");
        }
    }

    public void autentificar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite seu e-mail para receber o código 2FA: ");
        String emailDestino = sc.nextLine();

        String emailRemetente = "lucaskaua2003@gmail.com";
        String senhaRemetente = "myxa fwhh phor geel";

        boolean autenticado = Autenticador2FA.autenticar(emailDestino, emailRemetente, senhaRemetente);

        if (autenticado) {
            System.out.println("Acesso permitido");
        } else {
            System.out.println("Acesso negado");
        }
    }

    public List<Credenciais> getCredenciais() {
        return this.credenciais;
    }
}