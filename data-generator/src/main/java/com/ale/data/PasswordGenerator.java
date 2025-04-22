package com.ale.data;

import java.security.SecureRandom;


public class PasswordGenerator {

    private static final String NUMBERS = "0123456789";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String COMMON_SYMBOLS_F = "()`~!@#$%^&*-_+=|{}[]:;'<>,.?";

    // 过滤相似字符（类似：i, l, 1, L, o, O, 0）
    private static final String SIMILAR_CHARACTERS = "ilLoO10";
    private static final String COMMON_SYMBOLS = "~!@#$%^&*()_+";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int numberOfPasswords = 6; // 生成数量
        int passwordLength = 8;   // 密码长度

        boolean includeNumbers = true;
        boolean includeLowercase = true;
        boolean includeUppercase = true;
        boolean includeSymbols = true;
        boolean excludeSymbols = true;

        String characterSet = createCharacterSet(includeNumbers, includeLowercase,
                includeUppercase, includeSymbols, excludeSymbols);

        validateCharacterSet(characterSet, passwordLength);

        for (int i = 0; i < numberOfPasswords; i++) {
            String password = generatePassword(passwordLength, characterSet);
            System.out.println("Generated Password: " + password);
        }
    }

    private static String createCharacterSet(boolean includeNumbers, boolean includeLowercase,
                                             boolean includeUppercase, boolean includeSymbols,
                                             boolean excludeSimilarCharacters) {
        StringBuilder sb = new StringBuilder();
        if (includeNumbers) {
            sb.append(NUMBERS);
        }
        if (includeLowercase) {
            sb.append(LOWERCASE_LETTERS);
        }
        if (includeUppercase) {
            sb.append(UPPERCASE_LETTERS);
        }
        if (includeSymbols) {
            sb.append(COMMON_SYMBOLS);
        }
        if (excludeSimilarCharacters) {
            return excludeChars(sb.toString(), SIMILAR_CHARACTERS);
        }

        return sb.toString();
    }

    public static String excludeChars(String input, String charsToExclude) {
        // 构建正则表达式，匹配要排除的字符集合
        String regex = "[" + charsToExclude + "]";
        // 使用replaceAll替换掉所有匹配的字符
        return input.replaceAll(regex, "");
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