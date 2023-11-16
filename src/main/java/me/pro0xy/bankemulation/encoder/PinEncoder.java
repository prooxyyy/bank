package me.pro0xy.bankemulation.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 03:20</p>
 */

public class PinEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8));

            // Convert bytes to hexadecimal representation
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", hashedByte));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding pin code", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Encode the raw password and compare it with the stored encoded password
        return encode(rawPassword).equals(encodedPassword);
    }
}

