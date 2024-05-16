package tests;

import ciphers.PlayfairCipher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayfairCipherTest {

    @Test
    public void testEncryptionDecryption() {
        // Test plaintext
        String plaintext = "WORLD";
        String key = "KEY";

        // Create PlayfairCipher instance
        PlayfairCipher cipher = new PlayfairCipher(key);

        // Encrypt plaintext
        String encryptedText = cipher.encrypt(plaintext);

        // Decrypt ciphertext
        String decryptedText = cipher.decrypt(encryptedText);

        String expectedText = "WORLDX";

        // Check if decrypted text matches original plaintext
        assertEquals(expectedText, decryptedText);
    }

    @Test
    public void testEncryptionDecryption1() {
        // Test plaintext
        String plaintext = "HELLO";
        String key = "KEY";

        // Create PlayfairCipher instance
        PlayfairCipher cipher = new PlayfairCipher(key);

        // Encrypt plaintext
        String encryptedText = cipher.encrypt(plaintext);

        // Decrypt ciphertext
        String decryptedText = cipher.decrypt(encryptedText);

        String expectedString = "HELXLO";

        // Check if decrypted text matches original plaintext
        assertEquals(expectedString, decryptedText);
    }

    @Test
    public void testEncryptionDecryption2() {
        String plaintext = "BARZ";
        String key = "KEY";

        PlayfairCipher cipher = new PlayfairCipher(key);

        String encryptedText = cipher.encrypt(plaintext);

        String decryptedText = cipher.decrypt(encryptedText);

        String expectedString = "BARZ";

        assertEquals(expectedString, decryptedText);
    }

}
