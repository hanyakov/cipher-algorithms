package main;

import javax.crypto.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
// import java.util.Scanner;
import ciphers.*;

public class Driver {

    public static void main(String[] args) throws Exception {
        List<Long> timings = new ArrayList<>();
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();

        CaesarCipher caesarCipher = new CaesarCipher();
        VigenereCipher vigenereCipher = new VigenereCipher();

        // Scanner scanner = new Scanner(System.in);
        int key = caesarCipher.generateKey();
        System.out.println("Generated Key: " + key);
        // System.out.print("Enter plaintext: ");
        // String plaintext1 = scanner.nextLine();
        String plaintext1 = readFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RandomText.txt");

        startTime = System.nanoTime();
        String ciphertext1 = caesarCipher.encrypt(plaintext1, key);
        System.out.println("Encrypted Text: " + ciphertext1);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        startTime = System.nanoTime();
        String decryptedText1 = caesarCipher.decrypt(ciphertext1, key);
        System.out.println("Decrypted Text: " + decryptedText1);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        String cipherName = caesarCipher.getName();
        System.out.println("Encrypted & decrypted using => " + cipherName);

        // write to a file
        writeToFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\CaesarCipherOutput.txt", decryptedText1);

        //////////////// vigenere testing
        String key2 = vigenereCipher.generateKey(3);
        System.out.println("Generated Key: " + key2);

        // Scanner scanner2 = new Scanner(System.in);
        // System.out.print("Enter plaintext: ");
        // String plaintext2 = scanner2.nextLine();
        String plaintext2 = readFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RandomText.txt");

        startTime = System.nanoTime();
        String ciphertext2 = vigenereCipher.encrypt(plaintext2, key2);
        System.out.println("Encrypted Text: " + ciphertext2);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        startTime = System.nanoTime();
        String decryptedtext2 = vigenereCipher.decrypt(ciphertext2, key2);
        System.out.println("Decrypted Text: " + decryptedtext2);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        String cipherName2 = vigenereCipher.getName();
        System.out.println("Encrypted & decrypted using => " + cipherName2);

        // write to a file
        writeToFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\VigenereCipherOutput.txt",
                decryptedtext2);

        ////////////// Row Transposition

        // Scanner scanners = new Scanner(System.in);
        // System.out.print("Enter plaintext: ");
        // String msg = scanners.nextLine();
        RowTrans.setPermutationOrder();
        String msg = readFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RandomText.txt");

        // Calling encryption function
        startTime = System.nanoTime();
        String cipher = RowTrans.encryptMessage(msg);
        System.out.println("Encrypted Message: " + cipher);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        // Calling Decryption function
        startTime = System.nanoTime();
        String decryptedMessage = RowTrans.decryptMessage(cipher);
        System.out.println("Decrypted Message: " + decryptedMessage);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        System.out.println("Encrypted & decrypted using => Row Transposition Cipher");

        // write to a file
        writeToFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RowTransOutput.txt", decryptedMessage);

        ////////////////////// DES

        try {
            String key4 = "01234567"; // 8-byte key
            // Scanner scanner4 = new Scanner(System.in);
            // System.out.print("Enter plaintext: ");
            // String plaintext4 = scanner4.nextLine();
            String plaintext4 = readFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RandomText.txt");

            System.out.println("Original Message: " + plaintext4);

            // Encrypt the message
            startTime = System.nanoTime();
            String ciphertext4 = DES.encrypt(plaintext4, key4);
            System.out.println("Encrypted Message: " + ciphertext4);
            endTime = System.nanoTime();
            timings.add(endTime - startTime);

            // Decrypt the message
            startTime = System.nanoTime();
            String decryptedtext4 = DES.decrypt(ciphertext4, key4);
            System.out.println("Decrypted Message: " + decryptedtext4);
            endTime = System.nanoTime();
            timings.add(endTime - startTime);
            System.out.println("Encrypted & decrypted using => DES cipher");

            // write to a file
            writeToFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\DESOutput.txt", decryptedtext4);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        /////////////////////////////////////////// AES
        try {
            // Generate AES key
            SecretKey key3 = AES.generateAESKey();
            // Scanner scanner3 = new Scanner(System.in);
            // System.out.print("Enter plaintext: ");
            // String plaintext3 = scanner3.nextLine();
            String plaintext3 = readFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RandomText.txt");
            // Original message

            System.out.println("Original Message: " + plaintext3);

            // Encrypt the message
            startTime = System.nanoTime();
            String ciphertext3 = AES.encrypt(plaintext3, key3);
            System.out.println("Encrypted Message: " + ciphertext3);
            endTime = System.nanoTime();
            timings.add(endTime - startTime);

            // Decrypt the message
            startTime = System.nanoTime();
            String decryptedtext3 = AES.decrypt(ciphertext3, key3);
            System.out.println("Decrypted Message: " + decryptedtext3);
            endTime = System.nanoTime();
            timings.add(endTime - startTime);

            System.out.println("Encrypted & decrypted using => AES cipher");

            // write to a file
            writeToFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\AESOutput.txt", decryptedtext3);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        ///////////////////////////// PLAYFAIR

        PlayfairCipher playfair = new PlayfairCipher("KEYWORD");

        playfair.displayMatrix();
        // Scanner scanner5 = new Scanner(System.in);
        // System.out.print("Enter plaintext: ");
        // String plaintext5 = scanner5.nextLine();
        String plaintext5 = readFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\RandomText.txt");
        startTime = System.nanoTime();
        String ciphertext5 = playfair.encrypt(plaintext5);
        System.out.println("Encrypted: " + ciphertext5);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);

        startTime = System.nanoTime();
        String decryptedtext5 = playfair.decrypt(ciphertext5);
        System.out.println("Decrypted: " + decryptedtext5);
        endTime = System.nanoTime();
        timings.add(endTime - startTime);
        System.out.println("Encrypted & decrypted using => Playfair cipher");

        // write to a file
        writeToFile("D:\\duck\\cipher-algorithms\\cipherAlgorithms\\src\\main\\PlayfairOutput.txt", decryptedtext5);

        System.out.println("Timings (in nanoseconds): " + timings);
    }

    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public static void writeToFile(String fileName, String content) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
