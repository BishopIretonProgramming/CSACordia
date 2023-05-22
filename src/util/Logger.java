package src.util;

//  imports
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

/**
 * A simple logger class with multiple logging levels for different occasions.
 * Each logging level has an effect on the output in the form of a color change.
 * The output will be shown in the format:
 * [HH:mm:ss:SSS]    [log number]    [cause]    LEVEL: message.
 * {@code INFO} level messages are displayed in green, {@code WARN} level messages
 * are displayed in orange, and {@code ERROR} level messages are displayed in red
 *
 * @author devinlinux
 * @version 1
 */
public class Logger {

    /*
     * The fields and constants that will be used throughout this class.
     * Each of the fields and constants are marked as private because there are
     * no other classes that need the ability to directly access or modify
     * these without the use of strictly defined getters and setters.
     */

    /**
     * The ANSI escape code sequence to reset the color of the output.
     * This will be used when the specific color for the level of the log
     * is no longer needed and the logger can return to using the normal
     * output color for logging.
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * The ANSI escape code sequence to set the output to the color
     * of red. This will be used for {@code ERROR} level messages
     * to alert the reader of the logs to the error.
     */
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * The ANSI escape code sequence to set the output to the color
     * of orange. This will be used for {@code WARN} level messages
     * to alert the reader of the logs to a warning.
     */
    private static final String ANSI_ORANGE = "\u001B[33m";

    /**
     * The ANSI escape code sequence to set the output to the color
     * of green. This will be used for {@code INFO} level messages
     * to alert the reader of the logs to an informational message.
     */
    private static final String ANSI_GREEN = "\u001B[32m";

    /**
     * The number of messages that have been logged in a single session
     * without resetting the log count.
     */
    private static int logNumber = 1;

    /**
     * The {@code Map} that will be used to store the log count for each
     * log level.
     */
    private static Map<Level, Integer> logCountMap = new HashMap<>();

    /**
     * A formatter that will be used to format the time in the log message.
     * Uses the {@code DateTimeFormatter}.
     */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    /**
     * The path to the log file where
     */
    private static Path logFilePath;

    static {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            String fileName = currentTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + ".log";
            logFilePath = Files.createFile(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Methods to log messages based on their specific levels.
     * Each of these methods will simply use the {@code log} method by calling
     * it with the added detail of the leve. This approach requires users of this
     * class to write less code since they do not have to specify the specific
     * level using the enum, they can simply call the level appropriate method.
     * The log method can also be called directly if the user prefers.
     */

    /**
     * Method to log a message with the specified log level, cause, and message
     *
     * @param level       The log level ({@code INFO}, {@code WARN}, {@code ERROR}).
     * @param cause       The cause of the log message.
     * @param message     The log message to be displayed.
     */
    public static void log(Level level, String cause, String message) {
        String levelColor = "";
        String levelString = level.toString();
        switch (level) {
            case INFO -> levelColor = ANSI_GREEN;
            case WARN -> levelColor = ANSI_ORANGE;
            case ERROR -> levelColor = ANSI_RED;
            default -> throw new IllegalArgumentException("Invalid log level: " + level);
        }

        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(formatter);

        int logCount = logCountMap.getOrDefault(level, 0) + 1;
        logCountMap.put(level, logCount);

        String logMessage = String.format("[%s]    [%d]    [%s]    %s%s%s: %s%n",
                formattedTime, logNumber, cause, levelColor, levelString, ANSI_RESET, message);
        logNumber++;

        System.out.print(logMessage);
        //write(logMessage);
    }

    /**
     * The available log levels represented by an enum.
     * The {@code INFO} level should be used for any informational messages.
     * The {@code WARN} level should be used for any messages that are not errors but could
     * potentially be problems or could potentially lead to problems.
     * The {@code ERROR} level should be used for any messages that indicate that an error
     * occurred in the run of the program and was either caught or what thrown.
     */
    public enum Level {
        /**
         * An informational level log
         */
        INFO,
        /**
         * A warning level log
         */
        WARN,
        /**
         * An error level log
         */
        ERROR
    }
}
