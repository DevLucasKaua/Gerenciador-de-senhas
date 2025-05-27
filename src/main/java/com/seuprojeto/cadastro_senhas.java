package com.seuprojeto;

import java.security.SecureRandom;

public class cadastro_senhas {
    //Pedi para gerar um conjunto de caracteres possiveis em uma senha e entao defini como possiveis para gerar a senha
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    public static String gerarSenha(int tamanho) {
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(tamanho); //Armazena os caracteres de acordo com o tamanho

        for (int i = 0; i < tamanho; i++) {
            senha.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            //O loop irá gerar numeros aleatorios até chegar na quantidade que foi dita no tamanho, a parte charAt irá
            //selecionar o caractere da lista e o append irá adcionar o caractere na senha
        }
        return senha.toString();
    }
}
