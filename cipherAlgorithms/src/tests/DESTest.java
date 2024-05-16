package tests;

import ciphers.DES;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DESTest {

    @Test
    public void testEncryptionDecryption() throws Exception {
        String plaintext = "This is a test message.";
        String key = "12345678";

        String encryptedText = DES.encrypt(plaintext, key);

        String decryptedText = DES.decrypt(encryptedText, key);

        assertEquals(plaintext, decryptedText);
    }
}
