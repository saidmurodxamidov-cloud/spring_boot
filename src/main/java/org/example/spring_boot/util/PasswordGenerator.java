package org.example.spring_boot.util;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    public static char[] generatePassword() {
        char[] password = new char[PASSWORD_LENGTH];

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int category = random.nextInt(3); // 0=lower, 1=upper, 2=digit
            char nextChar = switch (category) {
                case 0 -> // lowercase
                        (char) ('a' + random.nextInt(26));
                case 1 -> // uppercase
                        (char) ('A' + random.nextInt(26));
                default -> // digit
                        (char) ('0' + random.nextInt(10));
            };
            password[i] = nextChar;
        }

        return password;
    }
}