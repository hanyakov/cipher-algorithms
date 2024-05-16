package tests;

import ciphers.AES;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AESTest {

    @Test
    public void testEncryptionDecryption() throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKey key = AES.generateAESKey();

        String plaintext = "This is a test message or is it?";

        String encryptedText = AES.encrypt(plaintext, key);

        String decryptedText = AES.decrypt(encryptedText, key);

        assertEquals(plaintext, decryptedText);
    }
}
