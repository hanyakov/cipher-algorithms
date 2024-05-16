package tests;

import org.junit.Test;

import ciphers.CaesarCipher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CaesarCipherTest {

    @Test
    public void testEncryption() {
        CaesarCipher cipher = new CaesarCipher();
        String plainText = "HELLO";
        int key = 3;
        String encryptedText = cipher.encrypt(plainText, key);
        assertEquals("KHOOR", encryptedText);
    }

    @Test
    public void testDecryption() {
        CaesarCipher cipher = new CaesarCipher();
        String cipherText = "KHOOR";
        int key = 3;
        String decryptedText = cipher.decrypt(cipherText, key);
        assertEquals("HELLO", decryptedText);
    }

    @Test
    public void testEncryptionWithInvalidKey() {
        CaesarCipher cipher = new CaesarCipher();
        String plainText = "HELLO";
        int key = -1;
        assertNull(cipher.encrypt(plainText, key));
    }

    @Test
    public void testDecryptionWithInvalidKey() {
        CaesarCipher cipher = new CaesarCipher();
        String cipherText = "KHOOR";
        int key = -1;
        assertNull(cipher.decrypt(cipherText, key));
    }

    @Test
    public void testEncryptionWithBlankText() {
        CaesarCipher cipher = new CaesarCipher();
        String plainText = "";
        int key = 3;
        assertNull(cipher.encrypt(plainText, key));
    }

    @Test
    public void testDecryptionWithBlankText() {
        CaesarCipher cipher = new CaesarCipher();
        String cipherText = "";
        int key = 3;
        assertNull(cipher.decrypt(cipherText, key));
    }
}
