package io.github.fzdwx.lambada.lang;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.format.FastDateFormat;
import io.github.fzdwx.lambada.Exceptions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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

    /**
     * 偏移毫秒数
     *
     * @param date   日期
     * @param offset 偏移毫秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    static DateTime offsetMillisecond(Date date, int offset) {
        return offset(date, DateField.MILLISECOND, offset);
    }

    /**
     * 偏移秒数
     *
     * @param date   日期
     * @param offset 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date  is null
     */
    static DateTime offsetSecond(Date date, int offset) {
        return offset(date, DateField.SECOND, offset);
    }

    /**
     * 偏移分钟
     *
     * @param date   日期
     * @param offset 偏移分钟数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date  is null
     */
    static DateTime offsetMinute(Date date, int offset) {
        return offset(date, DateField.MINUTE, offset);
    }

    /**
     * 偏移小时
     *
     * @param date   日期
     * @param offset 偏移小时数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date  is null
     */
    static DateTime offsetHour(Date date, int offset) {
        return offset(date, DateField.HOUR_OF_DAY, offset);
    }

    /**
     * 偏移天
     *
     * @param date   日期
     * @param offset 偏移天数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date  is null
     */
    static DateTime offsetDay(Date date, int offset) {
        return offset(date, DateField.DAY_OF_YEAR, offset);
    }

    /**
     * 偏移周
     *
     * @param date   日期
     * @param offset 偏移周数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date  is null
     */
    static DateTime offsetWeek(Date date, int offset) {
        return offset(date, DateField.WEEK_OF_YEAR, offset);
    }

    /**
     * 偏移月
     *
     * @param date   日期
     * @param offset 偏移月数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date  is null
     */
    static DateTime offsetMonth(Date date, int offset) {
        return offset(date, DateField.MONTH, offset);
    }

    /**
     * 获取指定日期偏移指定时间后的时间，生成的偏移日期不影响原日期
     *
     * @param date      基准日期
     * @param dateField 偏移的粒度大小（小时、天、月等）{@link DateField}
     * @param offset    偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     * @throws NullPointerException on date or dateField is null
     */
    static DateTime offset(Date date, DateField dateField, int offset) throws NullPointerException {
        if (date == null || dateField == null) {
            Exceptions.npe("date or dateField is null");
        }
        return DateUtil.offset(date, dateField, offset);
    }

    /**
     * 检查两个时间段是否有时间重叠<br>
     * 重叠指两个时间段是否有交集
     *
     * @return true 表示时间有重合
     * @apiNote 如果其中有任一一个时间为null，则返回false
     * @see DateUtil#isOverlap
     */
    static boolean isOverlap(Date realStartTime, Date realEndTime,
                             Date startTime, Date endTime) {

        if (realStartTime == null || realEndTime == null || startTime == null || endTime == null) {
            return false;
        }
        return DateUtil.isOverlap(realStartTime, realEndTime, startTime, endTime);
    }

    /**
     * 检查两个时间段是否有时间重叠<br>
     * 重叠指两个时间段是否有交集
     *
     * @return true 表示时间有重合
     * @apiNote 如果其中有任一一个时间为null，则返回false
     * @see LocalDateTimeUtil#isOverlap
     */
    static boolean isOverlap(ChronoLocalDateTime<?> realStartTime, ChronoLocalDateTime<?> realEndTime,
                             ChronoLocalDateTime<?> startTime, ChronoLocalDateTime<?> endTime) {
        if (realStartTime == null || realEndTime == null || startTime == null || endTime == null) {
            return false;
        }

        return LocalDateTimeUtil.isOverlap(realStartTime, realEndTime, startTime, endTime);
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