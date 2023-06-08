package src.login;

/**
 * A class to represent an {@code Exception} that should
 * be thrown when there is an error with a username
 */
public class UsernameException extends Exception {
    
    /**
     * An enum to represent the errors that a username can have.
     */
    public enum UsernameErrors {
        ALREADY_EXISTS("Username already exists"),
        NULL_OR_EMPTY("Username cannot be null or empty"),
        ILLEGAL_CHARACTER("Username cannot contain an illegal character"),
        LENGTH_TOO_SHORT("Username length must be greater than or equal to 3 characters"),
        LENGTH_TOO_LONG("Username length must be less than 21 characters");

        /**
         * The message associated with each username error
         */
        private final String MESSAGE;

        /**
         * Constructor to make a new username error with a message.
         *
         * @param MESSAGE the message associated with the username error.
         */
        UsernameErrors(final String MESSAGE) {
            this.MESSAGE = MESSAGE;
        }

        /**
         * Getter to return the message associated with a username error.
         *
         * @return the message associated with the username error.
         */
        public String message() {
            return this.MESSAGE;
        }
    }

    /**
     * Constructor to make a new {@code UsernameException} using one of the 
     * values in the enum {@code UsernameErrors}.
     *
     * @param error the value in the {@code UsernameErrors} enum to make the 
     *              exception from.
     */
    public UsernameException(UsernameErrors error) {
        super(error.message());
    }
}
