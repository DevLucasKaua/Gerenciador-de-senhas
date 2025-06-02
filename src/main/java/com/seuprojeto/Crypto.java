package com.seuprojeto;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class Crypto {

    private static final String CONFIG_PATH = "config/crypto.properties";
    private static final int IV_LENGTH = 12; // Recomendado para GCM
    private static final int TAG_LENGTH_BIT = 128; // Tag de autenticação GCM

    private Crypto() {
        throw new UnsupportedOperationException("Classe utilitária - não deve ser instanciada.");
    }

    // Exceção para problemas na chave
    public static class CryptoKeyException extends RuntimeException {
        public CryptoKeyException(String message, Throwable cause) {
            super(message, cause);
        }

        public CryptoKeyException(String message) {
            super(message);
        }
    }

    // Exceção para falhas na criptografia/descriptografia
    public static class CryptoOperationException extends RuntimeException {
        public CryptoOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private static SecretKeySpec getSecretKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_PATH)) {
            properties.load(input);
            String key = properties.getProperty("secret.key");
            if (key == null || key.length() != 16) {
                throw new CryptoKeyException("A chave deve ter exatamente 16 caracteres.");
            }
            return new SecretKeySpec(key.getBytes(), "AES");
        } catch (IOException e) {
            throw new CryptoKeyException("Erro ao carregar a chave secreta do arquivo.", e);
        }
    }

    public static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = getSecretKey();
            byte[] iv = new byte[IV_LENGTH];
            new SecureRandom().nextBytes(iv);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
            byte[] encrypted = cipher.doFinal(data.getBytes());

            byte[] encryptedWithIv = new byte[IV_LENGTH + encrypted.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, IV_LENGTH);
            System.arraycopy(encrypted, 0, encryptedWithIv, IV_LENGTH, encrypted.length);

            return Base64.getEncoder().encodeToString(encryptedWithIv);
        } catch (Exception e) {
            throw new CryptoOperationException("Erro na criptografia", e);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = getSecretKey();
            byte[] encryptedWithIv = Base64.getDecoder().decode(encryptedData);

            byte[] iv = new byte[IV_LENGTH];
            byte[] encrypted = new byte[encryptedWithIv.length - IV_LENGTH];
            System.arraycopy(encryptedWithIv, 0, iv, 0, IV_LENGTH);
            System.arraycopy(encryptedWithIv, IV_LENGTH, encrypted, 0, encrypted.length);

            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);

            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted);
        } catch (Exception e) {
            throw new CryptoOperationException("Erro na descriptografia", e);
        }
    }
}