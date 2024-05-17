package tests;

import ciphers.VigenereCipher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VigenereCipherTest {

    @Test
    public void testEncryptionDecryption1() {
        String plaintext = "HELLO";
        String key = "KEY";

        VigenereCipher cipher = new VigenereCipher();

        String encryptedText = cipher.encrypt(plaintext, key);

        String decryptedText = cipher.decrypt(encryptedText, key);

        assertEquals(plaintext, decryptedText);
    }

    @Test
    public void testEncryptionDecryption2() {
        String plaintext = "HOLA, MUNDO!";
        String key = "KEY";

        VigenereCipher cipher = new VigenereCipher();

        String encryptedText = cipher.encrypt(plaintext, key);

        String decryptedText = cipher.decrypt(encryptedText, key);

        assertEquals(plaintext, decryptedText);
    }
}
