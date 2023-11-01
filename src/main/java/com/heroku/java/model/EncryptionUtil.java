package com.heroku.java.model;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class EncryptionUtil {
    // Initialize Argon2 instance
    private static final Argon2 argon2 = Argon2Factory.create();

    public static String hashPassword(String password) {
        // Hash the password using Argon2
        return argon2.hash(10, 65536, 1, password);
    }

    public static boolean verifyPassword(String hashedPassword, String inputPassword) {
        // Verify the input password against the hashed password
        return argon2.verify(hashedPassword, inputPassword);
    }
}
