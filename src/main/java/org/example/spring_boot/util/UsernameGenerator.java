package org.example.util;

import java.util.Set;

public class UsernameGenerator {

    public static String generateUsername(String firstName, String lastName, Set<String> existingUsernames) {
        String baseUsername = (firstName + "." + lastName).toLowerCase();
        String username = baseUsername;
        int counter = 1;

        while (existingUsernames.contains(username)) {
            username = baseUsername + counter;
            counter++;
        }

        return username;
    }
}
