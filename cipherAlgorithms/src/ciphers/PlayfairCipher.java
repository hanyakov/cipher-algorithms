package ciphers;

import java.util.Arrays;

public class PlayfairCipher {
    private char[][] matrix;
    private String key;

    public PlayfairCipher(String key) {
        this.key = key;
        generateMatrix();
    }

    private void generateMatrix() {
        // Remove duplicate characters from the key
        String refinedKey = removeDuplicates(key);

        // Create a matrix to hold the Playfair cipher key
        matrix = new char[5][5];
        int rowIndex = 0;
        int colIndex = 0;

        // Fill the matrix with the characters from the refined key
        for (int i = 0; i < refinedKey.length(); i++) {
            if (refinedKey.charAt(i) == 'J') {
                continue; // Replace 'J' with 'I'
            }
            matrix[rowIndex][colIndex] = refinedKey.charAt(i);
            colIndex++;
            if (colIndex == 5) {
                colIndex = 0;
                rowIndex++;
            }
        }

        // Fill the remaining matrix positions with the alphabet (excluding 'J')
        char currentChar = 'A';
        while (rowIndex < 5) {
            if (currentChar == 'J') {
                currentChar++;
            }
            boolean charFound = false;
            for (int i = 0; i < refinedKey.length(); i++) {
                if (refinedKey.charAt(i) == currentChar) {
                    charFound = true;
                    break;
                }
            }
            if (!charFound) {
                matrix[rowIndex][colIndex] = currentChar;
                colIndex++;
                if (colIndex == 5) {
                    colIndex = 0;
                    rowIndex++;
                }
            }
            currentChar++;
        }
    }

    private String removeDuplicates(String key) {
        StringBuilder refinedKey = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            if (currentChar == 'J') {
                currentChar = 'I'; // Replace 'J' with 'I'
            }
            if (refinedKey.indexOf(String.valueOf(currentChar)) == -1) {
                refinedKey.append(currentChar);
            }
        }
        return refinedKey.toString();
    }

    private String preparePlainText(String plainText) {
        // Remove spaces and convert to uppercase
        return plainText.replaceAll("\\s+", "").toUpperCase();
    }

    public String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();
        plainText = preparePlainText(plainText);
        for (int i = 0; i < plainText.length(); i += 2) {
            char firstChar = plainText.charAt(i);
            char secondChar = (i + 1 < plainText.length()) ? plainText.charAt(i + 1) : 'X';
            if (firstChar == secondChar) {
                secondChar = 'X'; // Add an 'X' if two consecutive characters are the same
                i--; // Process the same character again
            }
            int[] firstCharIndex = findCharIndex(firstChar);
            int[] secondCharIndex = findCharIndex(secondChar);
            if (firstCharIndex[0] == secondCharIndex[0]) { // Same row
                encryptedText.append(matrix[firstCharIndex[0]][(firstCharIndex[1] + 1) % 5]);
                encryptedText.append(matrix[secondCharIndex[0]][(secondCharIndex[1] + 1) % 5]);
            } else if (firstCharIndex[1] == secondCharIndex[1]) { // Same column
                encryptedText.append(matrix[(firstCharIndex[0] + 1) % 5][firstCharIndex[1]]);
                encryptedText.append(matrix[(secondCharIndex[0] + 1) % 5][secondCharIndex[1]]);
            } else { // Form a rectangle
                encryptedText.append(matrix[firstCharIndex[0]][secondCharIndex[1]]);
                encryptedText.append(matrix[secondCharIndex[0]][firstCharIndex[1]]);
            }
        }
        return encryptedText.toString();
    }

    public String decrypt(String encryptedText) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i += 2) {
            char firstChar = encryptedText.charAt(i);
            char secondChar = encryptedText.charAt(i + 1);
            int[] firstCharIndex = findCharIndex(firstChar);
            int[] secondCharIndex = findCharIndex(secondChar);
            if (firstCharIndex[0] == secondCharIndex[0]) { // Same row
                decryptedText.append(matrix[firstCharIndex[0]][(firstCharIndex[1] + 4) % 5]);
                decryptedText.append(matrix[secondCharIndex[0]][(secondCharIndex[1] + 4) % 5]);
            } else if (firstCharIndex[1] == secondCharIndex[1]) { // Same column
                decryptedText.append(matrix[(firstCharIndex[0] + 4) % 5][firstCharIndex[1]]);
                decryptedText.append(matrix[(secondCharIndex[0] + 4) % 5][secondCharIndex[1]]);
            } else { // Form a rectangle
                decryptedText.append(matrix[firstCharIndex[0]][secondCharIndex[1]]);
                decryptedText.append(matrix[secondCharIndex[0]][firstCharIndex[1]]);
            }
        }
        return decryptedText.toString();
    }

    private int[] findCharIndex(char c) {
        int[] index = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == c) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return index;
    }

    public void displayMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

    }
}