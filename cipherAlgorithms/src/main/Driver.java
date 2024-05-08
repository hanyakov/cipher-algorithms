package main;

import java.util.Scanner;

import ciphers.CaesarCipher;

public class Driver {
    public static void main(String[] args) throws Exception {
        CaesarCipher caesarCipher = new CaesarCipher();

        int key = caesarCipher.generateKey();
        System.out.println("Generated Key: " + key);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();
        scanner.close();

        String ciphertext = caesarCipher.encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + ciphertext);

        String decryptedText = caesarCipher.decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
        
        String cipherName = caesarCipher.getName();
        System.out.println("Encrypted & decrypted using => " + cipherName);
    }
}
