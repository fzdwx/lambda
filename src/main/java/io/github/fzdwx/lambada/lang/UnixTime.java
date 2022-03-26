package io.github.fzdwx.lambada.lang;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * unix time.
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/17 11:23
 * @since 0.06
 */
public class UnixTime {

    private final long value;
    private final static long v = 1000L;

    /**
     * create now for unix time.
     *
     * @return {@link UnixTime }
     */
    public static UnixTime now() {
        return new UnixTime();
    }

    /**
     * create unix time from value.
     *
     * @param value unix time value
     * @return {@link UnixTime }
     */
    public static UnixTime of(long value) {
        return new UnixTime(value);
        // return new UnixTime((value / v) + t);
    }

    /**
     * unix time to long
     *
     * @return long
     */
    public static long unixTime() {
        return calValue() * v;
    }

    /**
     * get actual value.
     *
     * @return long
     */
    public long value() {
        return value;
    }

    /**
     * unix time to date.
     *
     * @return {@link Date }
     */
    public Date toDate() {
        return new Date(unixTime(value));
    }

    /**
     * unix time to local date time.
     *
     * @return {@link LocalDateTime }
     */
    public LocalDateTime toLocalDateTime() {
        return LocalDateTimeUtil.of(unixTime(value));
    }

    @Override
    public String toString() {
        return new Date(unixTime(value)).toString();
    }

    private UnixTime(long value) {
        this.value = value;
    }

    private UnixTime() {
        this(calValue());
    }

    private static long calValue() {
        return System.currentTimeMillis() / v;
    }

    private static long unixTime(long value) {
        return value * v;
    }
}