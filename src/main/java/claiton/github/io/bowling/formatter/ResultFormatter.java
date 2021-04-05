package claiton.github.io.bowling.formatter;

public interface ResultFormatter<T> {
    String getFormattedResult(T value);
}
