package tests;

import ciphers.RowTrans;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RowTransTest {

    @Before
    public void setUp() {
        RowTrans.setPermutationOrder();
    }

    @Test
    public void testEncryptionDecryption() {
        String message = "HELLO";

        String encryptedMessage = RowTrans.encryptMessage(message);

        String decryptedMessage = RowTrans.decryptMessage(encryptedMessage);

        assertEquals(message, decryptedMessage);
    }
}
