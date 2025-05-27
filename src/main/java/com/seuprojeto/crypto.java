package com.seuprojeto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class crypto {
    private static final String KEY = "123456789";
    //A chave de acesso não pode ficar no codigo mas assim facilita por enquanto

    public static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e){
            throw new RuntimeException("Erro na criptografia",e);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e){
            throw new RuntimeException("Erro na criptografia",e);
        }
    }
}
