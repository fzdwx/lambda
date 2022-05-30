package io.github.fzdwx.lambada;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 16:16
 */
public class Assert {

    public static void nonNull(Object o) {
        nonNull(o, "The validated object is null");
    }

    public static void nonNull(Object o, String message) {
        if (o == null) {
            Exceptions.illegalArgument(message);
        }
    }

    public static <T> T defVal(T t, final T defaultValue) {
        if (t == null) {
            return defaultValue;
        }
        return t;
    }

    public static void gt0(final int chunkSize, final String message) {
        if (chunkSize <= 0) {
            Exceptions.illegalArgument(message);
        }
    }
}