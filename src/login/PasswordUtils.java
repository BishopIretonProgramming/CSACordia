package src.login;

//  imports
import java.util.Random;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import static src.login.PasswordException.PasswordErrors;
import static src.login.PasswordUtils.ComplexAsciiRange.AsciiRange;

/**
 * A utility class to assist with the creation and validation of passwords.
 *
 * @author devinlinux
 */
public final class PasswordUtils{
   
    /**
     * Suppress the default no-args constructor provided by the compiler
     * to ensure that no one can create an instance of this class.
     */
    private PasswordUtils() {
    }

    /**
     * The minimum allowed password length.
     */
    private static final int MINIMUM_PASSWORD_LENGTH = 8;

    /**
     * The maximum allowed password length.
     */
    private static final int MAXIMUM_PASSWORD_LENGTH = 2140000001;

    /**
     * The {@code String} containing the uppercase letters, at least one must be 
     * contained in every password.
     */
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The {@code String} containing the lowercase letters, at least on must be 
     * contained in every password.
     */
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyk";

    /**
     * The {@code String} containing the symbols, at least one symbol
     * must be contained in every password.
     */
    private static final String SYMBOLS = new ComplexAsciiRange(
            new AsciiRange('!', '/'),
            new AsciiRange(':', '@'),
            new AsciiRange('[', '`'),
            new AsciiRange('{', '~'),
            new AsciiRange(128, Integer.MAX_VALUE - 2))
            .toString();

    /**
     * A method to generate a secure password that conforms to the password rules: each password 
     * must contain at least 1 uppercase letter, 1 lowercase letter, and 1 symbol with a length 
     * of greater than or equal to 8 characters and less than 2140000001.
     *
     * @return the {@code String} with the generated password.
     */
    public static String genPassword() {
        String charPool = UPPERCASE_LETTERS + LOWERCASE_LETTERS + SYMBOLS;
        Random rand = new Random();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < MINIMUM_PASSWORD_LENGTH; i++) 
            password.append(charPool.charAt(rand.nextInt(charPool.length())));
        
        int uppercaseIndex = rand.nextInt(MINIMUM_PASSWORD_LENGTH), lowercaseIndex = 0, symbolIndex = 0;
        while (lowercaseIndex != uppercaseIndex)
            lowercaseIndex = rand.nextInt(MINIMUM_PASSWORD_LENGTH);
        while (symbolIndex != uppercaseIndex && symbolIndex != lowercaseIndex)
            symbolIndex = rand.nextInt(MINIMUM_PASSWORD_LENGTH);
        
        password.setCharAt(uppercaseIndex, UPPERCASE_LETTERS.charAt(rand.nextInt(UPPERCASE_LETTERS.length())));
        password.setCharAt(lowercaseIndex, LOWERCASE_LETTERS.charAt(rand.nextInt(LOWERCASE_LETTERS.length())));
        password.setCharAt(symbolIndex, SYMBOLS.charAt(rand.nextInt(SYMBOLS.length())));

        return password.toString();
    }

    /**
     * A method to validate a password according to the password rules: each password must contain
     * at least 1 uppercase letter, 1 lowercase letter, and 1 symbol with a length of greater than
     * or equal to 8 characters and less than 2140000001 characters.
     *
     * @param password the password to validate according to the password rules.
     * @return         whether password is a valid password.
     * @throws PasswordException if the password is not a valid password.
     */
    public static boolean isValidPassword(final String password) throws PasswordException {
        if (password == null || password.equals("")) 
            throw new PasswordException(PasswordErrors.NULL_OR_EMPTY);
        if (password.length() < MINIMUM_PASSWORD_LENGTH)
            throw new PasswordException(PasswordErrors.LENGTH_TOO_SHORT);
        if (password.length() > MAXIMUM_PASSWORD_LENGTH)
            throw new PasswordException(PasswordErrors.LENGTH_TOO_LONG);
        if (password.contains(" "))
            throw new PasswordException(PasswordErrors.ILLEGAL_WHITESPACE);
        
        boolean hasUppercase = password.chars().anyMatch(c -> UPPERCASE_LETTERS.contains(Character.toString((char) c)));
        boolean hasLowercase = password.chars().anyMatch(c -> LOWERCASE_LETTERS.contains(Character.toString((char) c)));
        boolean hasSymbol = password.chars().anyMatch(c -> SYMBOLS.contains(Character.toString((char) c)));

        if (!hasUppercase) 
            throw new PasswordException(PasswordErrors.MISSING_UPPERCASE_LETTER);
        if (!hasLowercase)
            throw new PasswordException(PasswordErrors.MISSING_LOWERCASE_LETTER);
        if (!hasSymbol)
            throw new PasswordException(PasswordErrors.MISSING_SYMBOL);

        return true;
    }

    /**
     * A nested, static class to represent a complex range of ascii characters that has interruptions,
     * all bounds are inclusive.
     */
    protected static final class ComplexAsciiRange {
        
        /**
         * The array of {@code AsciiRange}s that make up this {@code ComplexAsciiRange}.
         */
        private final AsciiRange[] ranges;

        /**
         * Constructor to make a new {@code ComplexAsciiRange} using several {@code AsciiRange}s.
         *
         * @param ranges the {@code AsciiRange}s that should make up this {@code ComplexAsciiRange}.
         */
        private ComplexAsciiRange(AsciiRange... ranges) {
            this.ranges = ranges;
        }

        /**
         * toString method to return a {@code String} with all the characters contained
         * in each {@code AsciiRange} of this {@code ComplexAsciiRange}.
         *
         * @return a {@code String} with all the characters that this {@code ComplexAsciiRange}
         *         encompasses.
         */
        @Override
        public String toString() {
            return Arrays.stream(ranges)
                .map(AsciiRange::toString)
                .collect(Collectors.joining());
        }

        /**
         * A nested, static class to represent a range of ascii characters, all bounds are
         * inclusive. 
         *
         * @author devinlinux
         */
        protected static final class AsciiRange {

            /**
             * The lower bound of the range.
             */
            private final int lower;

            /**
             * The upper bound of the range.
             */
            private final int upper;

            /**
             * Constructor to make a new {@code AsciiRange} using integers.
             *
             * @param lower the lower bound of the range.
             * @param upper the upper bound of the range.
             */
            private AsciiRange(int lower, int upper) {
                this.upper = upper;
                this.lower = lower;
            }

            /**
             * Constructor to make a new {@code AsciiRange} using characters.
             *
             * @param lower the lower bound of the range.
             * @param upper the upper bound of the range.
             */
            private AsciiRange(char lower, char upper) {
                this.lower = lower;
                this.upper = upper;
            }

            /**
             * toString method to return a {@code String} with all the characters contained
             * within this range.
             *
             * @return a {@code String} with all the characters contained within this range.
             */
            @Override
            public String toString() {
                return IntStream.rangeClosed(this.lower, this.upper) 
                    .mapToObj(ch -> String.valueOf((char) ch))
                    .collect(Collectors.joining());
            }
        }
    }
}
