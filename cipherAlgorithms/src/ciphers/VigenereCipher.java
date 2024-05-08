package ciphers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class VigenereCipher {
    private final static String NAME = "Vigenere Cipher";
    private HashMap<Character, Integer> charMap;
    private final static char encryptionArr[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public VigenereCipher(){
    charMap=new HashMap<Character,Integer>();
    char startChar = 'A';for(
    int i = 0;i<26;i++){
        charMap.put((char) (startChar + i), i);
    }
}
    
    public String generateKey(int length){
		if(length <= 0){
			return null;
		}
		SecureRandom secureRandom = new SecureRandom();
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        int chosenLength = secureRandom.nextInt(length);
        if (chosenLength == 0) {
            chosenLength = 1;
        }
        String key = "";
        for (int i = 0; i < chosenLength; i++) {
            int randomValue = secureRandom.nextInt(26);
            key += encryptionArr[randomValue];
        }
        return key;
    }

    public String encrypt(String plainText, String key) {
        String encryptedText = "";
        if (plainText.length() <= 0 || key.length() <= 0) {
            return null;
        }
        plainText = plainText.trim();
        plainText = plainText.replaceAll("\\W", "");
        key = key.trim();
        if (plainText.contains(" ") || key.contains(" ")) {
            plainText = plainText.replaceAll(" ", "");
            key = key.replaceAll(" ", "");
        }
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < plainText.length(); i++) {
            char letter = plainText.charAt(i);
            char keyLetter = key.charAt((i % key.length()));
            int lookUp = (charMap.get(letter) + charMap.get(keyLetter)) % 26;
            encryptedText += encryptionArr[lookUp];
        }
        return encryptedText;
    }

    public String decrypt(String cipherText, String key) {
        String plainText = "";
        if (cipherText.length() <= 0 || key.length() <= 0) {
            return null;
        }
        cipherText = cipherText.trim();
        cipherText = cipherText.replaceAll("\\W", "");
        key = key.trim();
        if (cipherText.contains(" ") || key.contains(" ")) {
            cipherText = cipherText.replaceAll(" ", "");
            key = key.replaceAll(" ", "");
        }
        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            char keyLetter = key.charAt((i % key.length()));
            int lookUp = (charMap.get(letter) - charMap.get(keyLetter)) % 26;
            if (lookUp < 0) {
                lookUp += 26;
            }
            plainText += encryptionArr[lookUp];
        }
        return plainText;
    }

    public String getName() {
        return NAME;
    }

}