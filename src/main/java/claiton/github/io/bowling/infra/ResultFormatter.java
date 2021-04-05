package claiton.github.io.bowling.infra;

public interface ResultFormatter<T> {
    String getFormattedResult(T value);
}
