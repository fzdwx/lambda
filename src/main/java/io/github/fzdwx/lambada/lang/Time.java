package io.github.fzdwx.lambada.lang;

import cn.hutool.core.date.format.FastDateFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static cn.hutool.core.date.DatePattern.CHINESE_DATE_PATTERN;
import static cn.hutool.core.date.DatePattern.CHINESE_DATE_TIME_PATTERN;
import static cn.hutool.core.date.DatePattern.ISO8601_PATTERN;
import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_PATTERN;
import static cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;

/**
 * time util.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/17 11:23
 * @since 0.06
 */
public interface Time {

    /**
     * unix time
     *
     * @return long
     */
    static long unix() {
        return Instant.now().getEpochSecond();
    }

    /**
     * returns the current time in milliseconds.
     *
     * @return long
     */
    static long now() {
        return System.currentTimeMillis();
    }

    /**
     * @see System#nanoTime()
     */
    static long nano() {
        return System.nanoTime();
    }

    /**
     * new date.
     */
    static Date date() {
        return new Date();
    }

    /**
     * new localDateTime.
     */
    static LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    /**
     * no description.
     */
    static String format(long millis, String pattern) {
        return format(millis, FastDateFormat.getInstance(pattern));
    }

    /**
     * no description.
     */
    static String format(long millis, FastDateFormat format) {
        return format.format(millis);
    }

    /**
     * no description.
     */
    static String format(Date date, String pattern) {
        return format(date, FastDateFormat.getInstance(pattern));
    }

    /**
     * no description.
     */
    static String format(Date date, FastDateFormat format) {
        return format.format(date);
    }

    /**
     * no description.
     */
    static String format(LocalDateTime date, String pattern) {
        return format(date, FastDateFormat.getInstance(pattern));
    }

    /**
     * no description.
     */
    static String format(LocalDateTime date, FastDateFormat format) {
        return format.format(date);
    }

    /**
     * no description.
     */
    static String toDateTime() {
        return format(now(), Format.DATE_TIME);
    }

    /**
     * no description.
     */
    static String toDateTime(long millis) {
        return format(millis, Format.DATE_TIME);
    }

    /**
     * no description.
     */
    static String toDateTime(Date date) {
        return format(date, Format.DATE_TIME);
    }

    /**
     * no description.
     */
    static String toDateTime(LocalDateTime date) {
        return format(date, Format.DATE_TIME);
    }

    /**
     * no description.
     */
    static String toChineseDateTime() {
        return format(now(), Format.CHINESE_DATE_TIME);
    }

    /**
     * no description.
     */
    static String toChineseDateTime(Date date) {
        return format(date, Format.CHINESE_DATE_TIME);
    }

    /**
     * no description.
     */
    static String toChineseDateTime(LocalDateTime date) {
        return format(date, Format.CHINESE_DATE_TIME);
    }

    interface Format {

        /**
         * yyyy-MM-dd HH:mm:ss
         */
        FastDateFormat DATE_TIME = FastDateFormat.getInstance(NORM_DATETIME_PATTERN);

        /**
         * yyyy-MM-dd HH:mm:ss.SSS
         */
        FastDateFormat DATE_TIME_MS = FastDateFormat.getInstance(NORM_DATETIME_MS_PATTERN);

        /**
         * yyyy-MM-dd HH:mm:ss,SSS
         */
        FastDateFormat ISO8601 = FastDateFormat.getInstance(ISO8601_PATTERN);

        /**
         * yyyy年MM月dd日
         */
        FastDateFormat CHINESE_DATE = FastDateFormat.getInstance(CHINESE_DATE_PATTERN);

        /**
         * yyyy年MM月dd日HH时mm分ss秒
         */
        FastDateFormat CHINESE_DATE_TIME = FastDateFormat.getInstance(CHINESE_DATE_TIME_PATTERN);
    }
}