package com.seuprojeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerenciar {
    private List<Credenciais> listaCredenciais = new ArrayList<>();

    public void salvarCredenciais(Credenciais credenciais) {
        listaCredenciais.add(credenciais);
    }

    public void listarCredenciais(){
        if(listaCredenciais.isEmpty()){
            System.out.println("Nenhuma credencial encontrada");
            return;
        }
        for (Credenciais cred : listaCredenciais) {
            System.out.println("Email: " + cred.getEmail());
            System.out.println("Senha: " + crypto.decrypt(cred.getSenha()));
            System.out.println("----------------------------------------");
        }
    }

    public void autentificar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite seu e-mail para receber o código 2FA: ");
        String emailDestino = sc.nextLine();


        String emailRemetente = "lucas.kaua@souunit.com.br";
        String senhaRemetente = "myxa fwhh phor geel";

        boolean autenticado = Autenticador2FA.autenticar(emailDestino, emailRemetente, senhaRemetente);

        if (autenticado) {
            System.out.println("Acesso permitido");
        } else {
            System.out.println("Acesso negado");
        }
    }
}