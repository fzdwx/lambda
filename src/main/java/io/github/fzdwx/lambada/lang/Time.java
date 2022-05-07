package io.github.fzdwx.lambada.lang;

import java.time.Instant;

/**
 * time util.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/17 11:23
 * @since 0.06
 */
public class Time {

    /**
     * unix time
     *
     * @return long
     */
    public static long unix() {
        return Instant.now().getEpochSecond();
    }

    /**
     * returns the current time in milliseconds.
     *
     * @return long
     */
    public static long now() {
        return System.currentTimeMillis();
    }
}