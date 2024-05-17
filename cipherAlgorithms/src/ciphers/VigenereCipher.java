package ciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class VigenereCipher {
    private final static String NAME = "Vigenere Cipher";

    public String generateKey(int length) {
        if (length <= 0) {
            return null;
        }
        SecureRandom secureRandom = new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int chosenLength = secureRandom.nextInt(length);
        if (chosenLength == 0) {
            chosenLength = 1;
        }
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < chosenLength; i++) {
            int randomValue = secureRandom.nextInt(26);
            key.append((char) (randomValue + 'A'));
        }
        return key.toString();
    }

    public String encrypt(String plainText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        if (plainText.length() <= 0 || key.length() <= 0) {
            return null;
        }
        plainText = plainText.trim();
        key = key.trim();

        plainText = plainText.toUpperCase();
        key = key.toUpperCase();

        int j = 0;
        for (int i = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i);
            if (Character.isLetter(letter)) {
                encryptedText.append((char) ((letter + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            } else {
                encryptedText.append(letter);
            }
            j = j++ % key.length();

        }
        return encryptedText.toString();
    }

    public String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        if (cipherText.length() <= 0 || key.length() <= 0) {
            return null;
        }
        cipherText = cipherText.trim();
        key = key.trim();

        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();

        int j = 0;
        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            if (Character.isLetter(letter)) {
                plainText.append((char) ('Z' - (25 - (letter - key.charAt(j))) % 26));
            } else {
                plainText.append(letter);
            }
            j = j++ % key.length();
        }
        return plainText.toString();
    }

    public String getName() {
        return NAME;
    }

}