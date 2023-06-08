package src.login;

//  imports
import static src.login.UsernameException.UsernameErrors;
import static java.io.File.separatorChar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A library class to assist with the validation of usernames
 *
 * @author devinlinux
 */
public class UsernameUtils {
    
    /* 
     * Suppress the default no-args constructor provided by the compiler,
     * ensuring that no one can make an instance of this class.
     */
    private UsernameUtils() {
    }

    /**
     * The minimum length a username must be to be valid.
     */
    private static final int MINIMUM_USERNAME_LENGTH = 3;

    /**
     * The maximum length a username can be to be valid.
     */
    private static final int MAXIMUM_USERNAME_LENGTH = 20;

    /**
     * The greatest value that an ascii character in the username can be.
     */
    private static final int MAXIMUM_ASCII_VALUE = '~'; //  126

    /**
     * The file where the usernames are stored
     */
    private static final String USERNAMES_FILES = String.format("resources%clog_in%cuap%cusernames.dat", separatorChar, separatorChar, separatorChar);

    /**
     * Method to check if a username already exists
     *
     * @param  username the username to check for.
     * @return whether the username already exists.
     */
    private static boolean exists(final String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERNAMES_FILES))) {
            String line;
            while ((line = reader.readLine()) != null)
                if (line.equals(username))
                    return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to validate a username.
     *
     * @param  username the username to validate.
     * @return whether the username is valid.
     * @throws UsernameException if the username is invalid.
     */
    public static boolean isValidUsername(final String username) throws UsernameException {
        if (username == null || username.isEmpty()) throw new UsernameException(UsernameErrors.NULL_OR_EMPTY);
        if (username.length() < MINIMUM_USERNAME_LENGTH) throw new UsernameException(UsernameErrors.LENGTH_TOO_SHORT);
        if (username.length() > MAXIMUM_USERNAME_LENGTH) throw new UsernameException(UsernameErrors.LENGTH_TOO_LONG);
        if (username.chars().anyMatch(c -> c > MAXIMUM_ASCII_VALUE)) throw new UsernameException(UsernameErrors.ILLEGAL_CHARACTER);
        if (exists(username)) throw new UsernameException(UsernameErrors.ALREADY_EXISTS);
        return true;
    }
}
