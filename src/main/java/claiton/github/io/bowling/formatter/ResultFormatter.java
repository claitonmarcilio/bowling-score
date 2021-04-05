package claiton.github.io.bowling.formatter;

/**
 * This interface provides a generic result formatting mechanism
 */
public interface ResultFormatter<T> {

    /**
     * Formats a result
     *
     * @param value value to be formatted
     * @return a formatted result
     */
    String getFormattedResult(T value);
}
