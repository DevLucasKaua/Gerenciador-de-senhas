package com.seuprojeto;

import com.seuprojeto.crypto;
import com.seuprojeto.Credenciais;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerenciar {
    private List<Credenciais> credenciais = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();

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

    public void autentificar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();
        System.out.println("Digite seu senha: ");
        String senha = sc.nextLine();

        Credenciais usuario = null;

        for (Credenciais cred : credenciais) {
            if(cred.getEmail().equals(email) && crypto.decrypt(cred.getSenha()).equals(senha)){
                usuario = cred;
                break;
            }
        }

        if(usuario != null){
            String codigo2 = String.format("%06d", random.nextInt(999999));
            System.out.println("Seu codigo de autentificação é: "+codigo2);

            System.out.print("Digite o codigo: ");
            String codigo2Digitado = sc.nextLine();

            if (codigo2.equals(codigo2Digitado)) {
                System.out.println("Autentificação realizada com sucesso");
            }
            else {
                System.out.println("Codigo incorreto");
            }
        } else {
            System.out.println("Email ou senha incorreto");
        }
    }
}
