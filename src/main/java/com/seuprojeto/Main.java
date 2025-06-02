package com.seuprojeto;

import com.seuprojeto.Crypto;
import com.seuprojeto.Credenciais;
import com.seuprojeto.Gerenciar;
import com.seuprojeto.cadastro_senhas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gerenciar gerenciar = new Gerenciar();

        System.out.println("Gerenciador de Senhas");

        while (true) {
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1 - Adicionar uma credencial");
            System.out.println("2 - Gerar senha forte");
            System.out.println("3 - Mostrar as credenciais");
            System.out.println("4 - Autentificação de 2 fatores");
            System.out.println("5 - Sair");
            System.out.print("opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Usuario: ");
                    String usuario = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    String encryptedPassword = Crypto.encrypt(senha);
                    Credenciais credenciais = new Credenciais(usuario, encryptedPassword);
                    gerenciar.salvarCredenciais(credenciais);

                    System.out.println("Credenciais adicionada com sucesso!");
                    break;
                case 2:
                    String gerarSenha = cadastro_senhas.gerarSenha(16);
                    System.out.println("Senha gerada: " + gerarSenha);
                    break;
                case 3:
                    gerenciar.listarCredenciais();
                    break;
                case 4:
                    gerenciar.autentificar();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao invalida");
            }



        }
    }
}
