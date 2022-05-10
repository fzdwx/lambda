package io.github.fzdwx.lambada;

/**
 * exceptions.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/23 12:03
 */
public abstract class Exceptions {

    public static void illegalArgument(final String message) {
        throw new IllegalArgumentException(message);
    }

    public static void illegalArgument(final String message, final Throwable cause) {
        throw new IllegalArgumentException(message, cause);
    }

    public static IllegalArgumentException newIllegalArgument(final String message) {
        return new IllegalArgumentException(message);
    }

    public static IllegalArgumentException newIllegalArgument(final String message, final Throwable cause) {
        return new IllegalArgumentException(message, cause);
    }

    public static void illegalState(final String message) {
        throw new IllegalStateException(message);
    }

    public static IllegalStateException newIllegalState(final String message) {
        return new IllegalStateException(message);
    }

    public static void illegalState(final String message, final Throwable cause) {
        throw new IllegalStateException(message, cause);
    }

    public static IllegalStateException newIllegalState(final String message, final Throwable cause) {
        return new IllegalStateException(message, cause);
    }

    public static void npe(final String message) {
        throw new NullPointerException(message);
    }

    public static NullPointerException newNpe(final String message) {
        return new NullPointerException(message);
    }

    static class LambdaException extends RuntimeException {

        LambdaException(final Throwable t) {
            super(t);
        }

        LambdaException(String message) {
            super(message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return getCause() != null ? getCause().fillInStackTrace() :
                    super.fillInStackTrace();
        }
    }
}