
package com.seuprojeto;

import java.util.Random;
import java.util.Scanner;

public class Autenticador2FA {

    public static boolean autenticar(String emailDestino, String usuarioEmail, String senhaEmail) {
        String codigo = gerarCodigo();
        String assunto = "Seu código de autenticação 2FA";
        String corpo = "Seu código é: " + codigo;

        EmailSender.enviar(usuarioEmail, senhaEmail, emailDestino, assunto, corpo);

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o código enviado para seu e-mail: ");
        String codigoDigitado = sc.nextLine();

        if (codigoDigitado.equals(codigo)) {
            System.out.println("Autenticação 2FA bem-sucedida!");
            return true;
        } else {
            System.out.println("Código incorreto. Acesso negado.");
            return false;
        }
    }

    private static String gerarCodigo() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000); // Gera codigo de 6 digitos
        return String.valueOf(codigo);
    }
}