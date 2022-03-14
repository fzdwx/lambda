package io.github.fzdwx.lambada;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.text.StrPool;
import io.github.fzdwx.lambada.internal.LangUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        return StrFormatter.format(template, args);
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
    static boolean eq(double num1, double num2) {
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
    static boolean eq(float num1, float num2) {
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
    static boolean eq(long num1, long num2) {
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
    static boolean eq(BigDecimal bigNum1, BigDecimal bigNum2) {
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
     * 判断两个对象是否相等
     */
    static boolean eq(Object a, Object b) {
        return Objects.equals(a, b);
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
    static boolean eq(char c1, char c2, boolean caseInsensitive) {
        if (caseInsensitive) {
            return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }
        return c1 == c2;
    }

    /**
     * 如果 value 为null 则返回defVal
     *
     * @since 0.04
     */
    static <T> T defVal(T val, T defVal) {
        return val == null ? defVal : val;
    }

    /**
     * 如果 value 为null 则返回defVal
     *
     * @since 0.04
     */
    static String defVal(String value, String defVal) {
        return StringUtils.isBlank(value) ? defVal : value;
    }

    /**
     * 检查 CharSequence 是否为空 ("")、null 或仅空格。
     * 空白由Character.isWhitespace(char)定义。
     * isBlank(null)      = true
     * isBlank("")        = true
     * isBlank(" ")       = true
     * isBlank("bob")     = false
     * isBlank("  bob  ") = false
     *
     * @param s str
     * @return boolean
     * @since 0.04
     */
    static boolean isBlank(String s) {
        return StringUtils.isBlank(s);
    }

    /**
     * 判断 obj 是否为null
     *
     * @since 0.04
     */
    static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 数组是否为空
     *
     * @param <T>   数组元素类型
     * @param array 数组
     * @return 是否为空
     * @since 0.04
     */
    static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检查给定的CharSequence既不是null也不是长度为 0。
     * 注意：对于纯粹由空格组成的CharSequence ，此方法返回true 。
     * <p>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     *
     * @param str 要检查的String （可能为null ）
     * @return 如果String不为null并且有长度，则为true
     * @see #hasText(CharSequence)
     * @since 0.04
     */
    static boolean hasLength(@Nullable CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 检查给定的String是否包含实际的text 。
     * 更具体地说，如果String不为null 、长度大于 0 且包含至少一个非空白字符，则此方法返回true 。
     *
     * @param str 要检查的String （可能为null ）
     * @return 如果String不是null ，它的长度大于 0，并且它不只包含空格，则为true
     * @since 0.04
     */
    static boolean hasText(@Nullable CharSequence str) {
        return (str != null && str.length() > 0 && LangUtil.containsText(str));
    }

    /**
     * 判断obj是否为数组
     *
     * @param obj obj
     * @return boolean
     * @since 0.04
     */
    static boolean isArray(Object obj) {
        return null != obj && obj.getClass().isArray();
    }

    /**
     * 将提供的文本拆分为一个数组，指定分隔符。这是使用 StringTokenizer 的替代方法。
     * 返回的字符串数组中不包含分隔符。相邻的分隔符被视为一个分隔符。要对拆分进行更多控制，请使用 StrTokenizer 类。
     * null输入字符串返回null 。一个null的 separatorChars 在空白处分割。
     *
     * @param text 要解析的字符串，可能为 null
     * @return {@link List<String> }
     * @since 0.04
     */
    static List<String> split(String text) {
        final String[] split = StringUtils.split(text, StrPool.COMMA);
        if (split == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(split);
    }

    /**
     * 将提供的文本拆分为一个数组，指定分隔符。这是使用 StringTokenizer 的替代方法。
     * 返回的字符串数组中不包含分隔符。相邻的分隔符被视为一个分隔符。要对拆分进行更多控制，请使用 StrTokenizer 类。
     * null输入字符串返回null 。一个null的 separatorChars 在空白处分割。
     *
     * @param text    要解析的字符串，可能为 null
     * @param pattern 用作分隔符的字符
     * @return {@link List<String> }
     * @since 0.04
     */
    static List<String> split(String text, String pattern) {
        final String[] split = StringUtils.split(text, pattern);
        if (split == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(split);
    }

    /**
     * 将提供的Iterable的元素连接到包含提供的元素的单个字符串中。
     * 列表前后不加分隔符。 null分隔符与空字符串 ("") 相同。
     *
     * @param iterable    提供要连接在一起的值的Iterable ，可以为 null
     * @param conjunction 要使用的分隔符，null 被视为 ""
     * @return {@link String }
     * @since 0.04
     */
    static <T> String join(final Iterable<T> iterable, final String conjunction) {
        return StringUtils.join(iterable, conjunction);
    }

    /**
     * 以 conjunction 为分隔符将多个对象转换为字符串
     *
     * @param <T>       元素类型
     * @param delimiter 要使用的分隔符，null 被视为“”
     * @param array     提供要连接在一起的值的可变参数。 null元素被视为“”
     * @return 连接后的字符串
     * @since 0.04
     */
    static <T> String join(final String delimiter, final Object... array) {
        return StringUtils.joinWith(delimiter, array);
    }

    /**
     * 以 {@link StrPool#COMMA} 为分隔符将多个对象转换为字符串 使用逗号分割
     *
     * @param <T>      元素类型
     * @param iterable 集合
     * @return 连接后的字符串
     * @since 0.04
     */
    static <T> String join(final Iterable<T> iterable) {
        return StringUtils.join(iterable, StrPool.COMMA);
    }
}