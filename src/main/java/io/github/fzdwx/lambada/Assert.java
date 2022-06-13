package io.github.fzdwx.lambada;

import java.util.Collection;
import java.util.Map;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 16:16
 */
public class Assert {

    public static void ifTrue(boolean condition) {
        ifTrue(condition, "the condition must be false");
    }

    public static void ifTrue(final boolean condition, final String message) {
        if (condition) {
            Exceptions.illegalArgument(message);
        }
    }

    public static void ifFalse(boolean condition) {
        ifTrue(condition, "the condition must be true");
    }

    public static void ifFalse(final boolean condition, final String message) {
        if (condition) {
            Exceptions.illegalArgument(message);
        }
    }

    public static void nonNull(Object o) {
        nonNull(o, "The validated object is null");
    }

    public static void nonNull(Object o, String message) {
        if (o == null) {
            Exceptions.illegalArgument(message);
        }
    }

    public static void notEmpty(Collection<?> c) {
        notEmpty(c, "the collection must not empty");
    }

    public static void notEmpty(Collection<?> c, String message) {
        if (c == null || c.isEmpty()) {
            Exceptions.illegalArgument(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "the map must not empty");
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            Exceptions.illegalArgument(message);
        }
    }

    public static void notBlank(String val) {
        notBlank(val, "the val is must not blank");
    }

    public static void notBlank(String val, String message) {
        if (Lang.isBlank(val)) {
            Exceptions.illegalArgument(message);
        }
    }

    public static <T> T defVal(T t, final T defaultValue) {
        if (t == null) {
            return defaultValue;
        }
        return t;
    }

    /**
     * greater or equal to
     *
     * @return {@code actual} greater or equal to {@code expect} will return false,otherwise return true
     */
    public static boolean gt(final int actual, final int expect) {
        return actual <= expect;
    }

    public static boolean gt0(final int actual) {
        return gt(actual, 0);
    }

    public static void gt0(final int actual, final String message) {
        if (gt0(actual)) {
            Exceptions.illegalArgument(message);
        }
    }

    /**
     * Can be null at the same time, but not one of them
     *
     * @return {@code true}: One of them is null.{@code false}: Both are null,or neither is null
     */
    public static boolean bothNullOrBothNotNull(Object o1, Object o2) {
        final boolean o1b = o1 == null;
        final boolean o2b = o2 == null;

        if (o1b && o2b) {
            return false;
        }

        if (o1b) {
            return true;
        }

        return o2b;
    }

    /**
     * Can be null at the same time, but not one of them
     *
     * @param message when o1 or o2 is null throws message.
     * @throws IllegalArgumentException One of them is null will throw,both are null,or neither is null not throw
     * @see #bothNullOrBothNotNull(Object, Object)
     */
    public static void bothNullOrBothNotNull(Object o1, Object o2, String message) {
        final boolean o1b = o1 == null;
        final boolean o2b = o2 == null;

        if (o1b && o2b) {
            return;
        }

        if (o1b) {
            Exceptions.illegalArgument(message);
        }

        if (o2b) {
            Exceptions.illegalArgument(message);
        }
    }

    /**
     * Can be null at the same time, but not one of them
     *
     * @param o1Message when o1 is null throws message
     * @param o2Message when o2 is null throws message
     * @throws IllegalArgumentException One of them is null will throw,both are null,or neither is null not throw
     * @see #bothNullOrBothNotNull(Object, Object)
     */
    public static void bothNullOrBothNotNull(Object o1, Object o2, String o1Message, String o2Message) {
        final boolean o1b = o1 == null;
        final boolean o2b = o2 == null;

        if (o1b && o2b) {
            return;
        }

        if (o1b) {
            Exceptions.illegalArgument(o1Message);
        }

        if (o2b) {
            Exceptions.illegalArgument(o2Message);
        }
    }
}