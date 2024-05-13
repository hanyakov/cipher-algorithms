package main;

import java.util.Scanner;
import ciphers.CaesarCipher;
import ciphers.RowTrans;
import ciphers.VigenereCipher;

public class Driver {
    public static void main(String[] args) throws Exception {
        CaesarCipher caesarCipher = new CaesarCipher();
        VigenereCipher vigenereCipher = new VigenereCipher();

        int key = caesarCipher.generateKey();
        System.out.println("Generated Key: " + key);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext1 = scanner.nextLine();


        String ciphertext1 = caesarCipher.encrypt(plaintext1, key);
        System.out.println("Encrypted Text: " + ciphertext1);

        String decryptedText1 = caesarCipher.decrypt(ciphertext1, key);
        System.out.println("Decrypted Text: " + decryptedText1);

        String cipherName = caesarCipher.getName();
        System.out.println("Encrypted & decrypted using => " + cipherName);

//////////////// vigenere testing
        String key2 = vigenereCipher.generateKey(3);
        System.out.println("Generated Key: " + key2);

        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext2 = scanner2.nextLine();

        String ciphertext2 = vigenereCipher.encrypt(plaintext2, key2);
        System.out.println("Encrypted Text: " + ciphertext2);

        String decryptedtext2 = vigenereCipher.decrypt(ciphertext2, key2);
        System.out.println("Encrypted Text: " + decryptedtext2);
        RowTrans.setPermutationOrder(); // Set permutation order

        String message = "HELLO WORLD bla bla bla";

            // Encrypt the message
        String encryptedMessage = RowTrans.encryptMessage(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message
        String decryptedMessage = RowTrans.decryptMessage(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
