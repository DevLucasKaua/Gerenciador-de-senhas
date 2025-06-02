package com.seuprojeto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class Crypto {
    private static final String CONFIG_PATH = "config/crypto.properties";

    private static SecretKeySpec getSecretKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_PATH)) {
            properties.load(input);
            String key = properties.getProperty("secret.key");

            if (key == null || key.length() != 16) {
                throw new IllegalArgumentException("A chave deve ter exatamente 16 caracteres.");
            }

            return new SecretKeySpec(key.getBytes(), "AES");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar a chave secreta do arquivo.", e);
        }
    }

    public static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = getSecretKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erro na criptografia", e);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = getSecretKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erro na descriptografia", e);
        }
    }
}