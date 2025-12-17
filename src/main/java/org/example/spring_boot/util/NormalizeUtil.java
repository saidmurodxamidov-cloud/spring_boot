package org.example.spring_boot.util;

public class NormalizeUtil {
    public static String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
