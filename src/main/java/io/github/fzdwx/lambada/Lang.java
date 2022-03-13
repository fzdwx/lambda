package io.github.fzdwx.lambada;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Duration;

/**
 * lang.
 * <p>
 * a function set.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/13 10:08
 */
public interface Lang {

    /**
     * 原型设计期间使用的实现的临时替代品
     *
     * @return {@link T}
     */
    static <T> T todo() {
        throw new RuntimeException("todo");
    }

    static <T> T todo(final String msg) {
        throw new RuntimeException(msg);
    }

    /**
     * 使当前执行的线程休眠（暂时停止执行）指定的毫秒数，取决于系统计时器和调度程序的精度和准确性。 该线程不会失去任何监视器的所有权。
     *
     * @param sleepTime 睡眠时间
     * @return 被中断返回false，否则true
     */
    static boolean sleep(final Duration sleepTime) {
        final long millis = sleepTime.toMillis();
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (final InterruptedException ignore) {
                return false;
            }
        }
        return true;
    }

    /**
     * string 格式化.
     * <p>
     * 使用`{}`占位符
     *
     * @param template 模板
     * @param args     参数
     * @return {@link String }
     */
    static String format(String template, Object... args) {
        return MessageFormat.format(template, args);
    }


    /**
     * 比较大小，值相等 返回true<br>
     * 此方法通过调用{@link Double#doubleToLongBits(double)}方法来判断是否相等<br>
     * 此方法判断值相等时忽略精度的，即0.00 == 0
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 是否相等
     * @since 0.01
     */
    static boolean equals(double num1, double num2) {
        return Double.doubleToLongBits(num1) == Double.doubleToLongBits(num2);
    }

    /**
     * 比较大小，值相等 返回true<br>
     * 此方法通过调用{@link Float#floatToIntBits(float)}方法来判断是否相等<br>
     * 此方法判断值相等时忽略精度的，即0.00 == 0
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 是否相等
     * @since 0.01
     */
    static boolean equals(float num1, float num2) {
        return Float.floatToIntBits(num1) == Float.floatToIntBits(num2);
    }

    /**
     * 比较大小，值相等 返回true<br>
     * 此方法修复传入long型数据由于没有本类型重载方法,导致数据精度丢失
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 是否相等
     * @since 0.01
     */
    static boolean equals(long num1, long num2) {
        return num1 == num2;
    }

    /**
     * 比较大小，值相等 返回true<br>
     * 此方法通过调用{@link BigDecimal#compareTo(BigDecimal)}方法来判断是否相等<br>
     * 此方法判断值相等时忽略精度的，即0.00 == 0
     *
     * @param bigNum1 数字1
     * @param bigNum2 数字2
     * @return 是否相等
     * @since 0.01
     */
    static boolean equals(BigDecimal bigNum1, BigDecimal bigNum2) {
        //noinspection NumberEquality
        if (bigNum1 == bigNum2) {
            // 如果用户传入同一对象，省略compareTo以提高性能。
            return true;
        }
        if (bigNum1 == null || bigNum2 == null) {
            return false;
        }
        return 0 == bigNum1.compareTo(bigNum2);
    }

    /**
     * 比较两个字符是否相同
     *
     * @param c1              字符1
     * @param c2              字符2
     * @param caseInsensitive 是否忽略大小写
     * @return 是否相同
     * @since 0.01
     */
    static boolean equals(char c1, char c2, boolean caseInsensitive) {
        if (caseInsensitive) {
            return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }
        return c1 == c2;
    }

}