package src.login;

//  imports
import static java.io.File.separatorChar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import java.util.Base64;

import java.util.prefs.Preferences;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class with methods that handle logging in to the program
 *
 * @author devinlinux
 */
public final class LoginSystem {

    /**
     * Suppress the default no-args constructor provided by the compiler,
     * ensuring that no one can create an instance of this class.
     */
    private LoginSystem() {
    }

    /**
     * The path to the file where the usernames are stored.
     */
    private static final String USERNAME_FILE_PATH = String.format("resources%suap%su.dat", separatorChar, separatorChar);

    /**
     * The path to the file where the passwords are stored.
     */
    private static final String PASSWORD_FILE_PATH = String.format("resouces%cuap%cp.dat", separatorChar, separatorChar);

    /**
     * The salt that is used for hashing.
     */
    private static final String SALT = SaltReader.readSalt();

    /**
     * A method to verify a username and password combination.
     *
     * @param username the username in the username and passord combination.
     * @param password the password in the username and password combination.
     * @return         whether username and password represent a valid username
     *                 and password combination.
     */
    public static boolean verifyLogin(final String username, final String password) {
        String hashedUsername = "", hashedPassword = "";
        try {
            hashedUsername = EncryptionUtil.hashWithSalt(username);
            hashedPassword = EncryptionUtil.hashWithSalt(password);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        try (BufferedReader userReader = new BufferedReader(new FileReader(USERNAME_FILE_PATH));
             BufferedReader passReader = new BufferedReader(new FileReader(PASSWORD_FILE_PATH))) {
            String userLine, passLine;
            while ((userLine = userReader.readLine()) != null && (passLine = passReader.readLine()) != null)
                if (userLine.trim().equals(hashedUsername) && passLine.trim().equals(hashedPassword))
                    return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * A method to create a new {@code User} with a username and password.
     *
     * @param username the username of the new {@code User}.
     * @param password the password of the new {@code User}.
     * @return         whether the new {@code User} was successfully created.
     *
     * @throws UsernameException if the username is invalid.
     * @throws PasswordException if the password is invalid.
     */
    public static boolean createUser(final String username, final String password) throws UsernameException, PasswordException {
        String hashedUsername = "", hashedPassword = "";
        try {
            hashedUsername = EncryptionUtil.hashWithSalt(username);
            hashedPassword = EncryptionUtil.hashWithSalt(password);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        UsernameUtils.isValidUsername(username);
        PasswordUtils.isValidPassword(username);

        try (BufferedWriter userWriter = new BufferedWriter(new FileWriter(USERNAME_FILE_PATH, true));
             BufferedWriter passWriter = new BufferedWriter(new FileWriter(PASSWORD_FILE_PATH, true))) {
            userWriter.write(hashedUsername + "\n");
            passWriter.write(hashedPassword + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * A nested, static class for generating the salt.
     *
     * @author deivnlinux
     */
    private static final class SaltReader {

        private static final String CONCORDIA_DEVICE_ID = "concordiaDeviceID";

        /**
         * Suppress the default no-args constructor provided by the compiler,
         * ensuring that no one can create an instance of this class.
         */
        private SaltReader() {
        }

        /**
         * A method to read the salt from the preferences API.
         *
         * @return the read salt {@code String}.
         */
        private static String readSalt() {
            Preferences prefs = Preferences.userRoot().node(SaltReader.class.getName());
            String salt = prefs.get(CONCORDIA_DEVICE_ID, null);
            if (salt == null) {
                salt = genSalt();
                prefs.put(CONCORDIA_DEVICE_ID, salt);
            }
            return salt;
        }

        /**
         * A method to generate a random salt, every salt should be unique to each 
         * device considering that this method uses {@code SecureRandom} and the
         * generated salt will be saved using the preferences API.
         *
         * @return the randomly generated salt {@code String}.
         */
        private static String genSalt() {
            StringBuilder sb = new StringBuilder(12981);
            SecureRandom random = new SecureRandom();
            for (int i = 0; i < 12981; i++) {
                sb.append((char) random.nextInt(Integer.MAX_VALUE - 2 - 32) + 32);
            }
            String salt = sb.reverse().toString();
            salt = salt.chars()
                .map(c -> ((c << 3) & 0xFF) ^ ((c >> 2) & 0xFF))
                .mapToObj(c -> (char) c)
                .map(c -> {
                    if (Character.isLetter(c))
                        return Character.isUpperCase(c) ? (char) ('Z' - (c - 'A')) : (char) ('z' - (c - 'a'));
                    else if (Character.isDigit(c))
                        return (char) ('9' - (c - '0'));
                    else 
                        return (char) (~c);
                })
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
            return salt;
        }

        /**
         * A method to remove the salt from preferences, if necessary.
         */
        private static void delSalt() {
            Preferences prefs = Preferences.userRoot().node(SaltReader.class.getName());
            prefs.remove(CONCORDIA_DEVICE_ID);
        }
    }

    /**
     * A nested, static class to assist with the encryption of usernames and passwords.
     *
     * @author devinlinux
     */
    private static final class EncryptionUtil {
        
        /**
         * Suppress the default, no-args constructor provided by the compiler
         * to ensure that no one can create an instance of this class.
         */
        private EncryptionUtil() {
        }

        /**
         * A method to hash something using a salt.
         *
         * @param str the {@code String} to hash.
         * @return the hashed version of str using salt.
         * @throws NoSuchAlgorithmException           if the requested cryptographics algorithm is not available.
         * @throws InvalidKeySpecException            if the provided key specification is invalid.
         * @throws NoSuchPaddingException             if the requested padding scheme is not available.
         * @throws InvalidAlgorithmParameterException if the algorithm parameters are invalid.
         * @throws InvalidKeyException                if the provided key is invalid.
         * @throws IllegalBlockSizeException          if the block size is not valid for the given cipher.
         * @throws BadPaddingException                if the padding is invalid or incorrect.
         */
        private static String hashWithSalt(final String str) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
            AlgorithmParameterSpec parameterSpec = new PBEParameterSpec(LoginSystem.SALT.getBytes(), 7842186);
            KeySpec keySpec = new PBEKeySpec(str.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithHmacSHA512AndAES_256");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("PBEWithHmacSHA512AndAES_256");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
        }
    }
}
