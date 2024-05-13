package ciphers;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CaesarCipher {
    private final static String NAME = "Caesar Cipher";

    public int generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return secureRandom.nextInt(26 + 1);
    }

    public String encrypt(String plainText, int key) {
        StringBuilder encryptedText = new StringBuilder();
        if (key < 0 || key > 25 || plainText.isBlank()) {
            return null;
        }
        //plainText = plainText.trim();
        plainText = plainText.replaceAll("\\W", "");
        plainText = plainText.replaceAll(" ", "");
        plainText = plainText.toUpperCase();

        for (int i = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i);
            int shifted = (letter - 'A' + key) % 26 + 'A';
            encryptedText.append((char) shifted);
        }
        return encryptedText.toString();
    }

    public String decrypt(String cipherText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        if (key < 0 || key > 25 || cipherText.isBlank()) {
            return null;
        }
        //cipherText = cipherText.trim();
        cipherText = cipherText.replaceAll("\\W", "");
        cipherText = cipherText.replaceAll(" ", "");
        cipherText = cipherText.toUpperCase();

        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            int shifted = (letter - 'A' - key + 26) % 26 + 'A';
            decryptedText.append((char) shifted);
        }
        return decryptedText.toString();
    }

    public String getName() {
        return NAME;
    }
}