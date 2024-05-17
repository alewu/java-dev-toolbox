package com.ale.data;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PasswordGenerator {

    private static final String NUMBERS = "23456789";
    private static final String LOWERCASE_LETTERS = "abcdefghjkmnpqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
    private static final String COMMON_SYMBOLS_F = "()`~!@#$%^&*-_+=|{}[]:;'<>,.?";

    private static final String COMMON_SYMBOLS = "()`~!@#$%^&*-_+=|{}[]:;'<>,.?";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int numberOfPasswords = 6; // 生成数量
        int passwordLength = 20;   // 密码长度

        boolean includeNumbers = true;
        boolean includeLowercase = true;
        boolean includeUppercase = true;
        boolean includeSymbols = true;

        String characterSet = createCharacterSet(includeNumbers, includeLowercase, includeUppercase, includeSymbols);

        validateCharacterSet(characterSet, passwordLength);

        for (int i = 0; i < numberOfPasswords; i++) {
            String password = generatePassword(passwordLength, characterSet);
            System.out.println("Generated Password: " + password);
        }
    }

    private static String createCharacterSet(boolean includeNumbers, boolean includeLowercase, boolean includeUppercase, boolean includeSymbols) {
        StringBuilder stringBuilder = new StringBuilder();
        if (includeNumbers) {
            stringBuilder.append(NUMBERS);
        }
        if (includeLowercase) {
            stringBuilder.append(LOWERCASE_LETTERS);
        }
        if (includeUppercase) {
            stringBuilder.append(UPPERCASE_LETTERS);
        }
        if (includeSymbols) {
            stringBuilder.append(COMMON_SYMBOLS);
        }

        return stringBuilder.toString();
    }

    private static void validateCharacterSet(String characterSet, int passwordLength) {
        if (characterSet.length() < passwordLength) {
            throw new IllegalArgumentException("Password length cannot be greater than the size of the character set.");
        }
    }

    public static String generatePassword(int length, String characterSet) {
        StringBuilder password = new StringBuilder();
        int i1 = characterSet.length();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(i1);
            password.append(characterSet.charAt(randomIndex));
        }
        return password.toString();
    }
}