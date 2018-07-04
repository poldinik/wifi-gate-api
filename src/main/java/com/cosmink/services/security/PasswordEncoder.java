package com.cosmink.services.security;

import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordEncoder {

    public static String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public static Boolean checkPassword(String plainTextPassword, String hashedPassword) {

        if (null == hashedPassword || !hashedPassword.startsWith("$2a$")) {
            throw new RuntimeException("Hashed password is invalid");
        }

        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
