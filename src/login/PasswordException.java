package src.login;

/**
 * A class to represent an {@code Exception} that is thrown
 * when there is a problem with a password.
 */
public class PasswordException extends Exception {
    
    protected enum PasswordErrors {
        NULL_OR_EMPTY("Password cannot be null or empty"),
        LENGTH_TOO_SHORT("Password length must be greater than or equal to 8 characters"),
        LENGTH_TOO_LONG("Password length must be less than 2140000001 characters"),
        MISSING_UPPERCASE_LETTER("Password must contain at least 1 uppercase letter"),
        MISSING_LOWERCASE_LETTER("Password must contain at least 1 lowercase letter"),
        MISSING_SYMBOL("Password must contain at least 1 symbol"),
        ILLEGAL_WHITESPACE("Password cannot contain a space");

        /**
         * The message of each password error
         */
        private final String MESSAGE;

        /**
         * Constructor to make a new password error with a specified message.
         *
         * @param MESSAGE the message of this password error.
         */
        PasswordErrors(final String MESSAGE) {
            this.MESSAGE = MESSAGE;
        }

        /**
         * Getter for the message of this password error.
         *
         * @return the message of this {@code PasswordException}.
         */
        public String message() {
            return this.MESSAGE;
        }
    }

    /**
     * Constructor to make a new {@code PasswordException} from
     * using a value in the {@code PasswordErrors} enum.
     */
    public PasswordException(PasswordErrors error) {
        super(error.message());
    }
}
