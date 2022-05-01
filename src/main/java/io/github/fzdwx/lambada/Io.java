package io.github.fzdwx.lambada;

import lombok.NonNull;

import java.io.ByteArrayInputStream;

/**
 * io util.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/21 15:04
 */
public interface Io {

    String EMPTY = "";

    /**
     * 包装 字节数组到 字节输入流
     *
     * @param bytes 字节数组
     * @return {@link ByteArrayInputStream }
     * @apiNote byte[] -> ByteArrayInputStream,bytes not null.
     */
    static ByteArrayInputStream wrap(byte @NonNull [] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * @see #read(String, int, int)
     */
    static String read(String str, int length) {
        return read(str, 0, length);
    }

    /**
     * @see #read(String, int, int)
     */
    static String readSuf(String str, int startIndexInclude) {
        return read(str, startIndexInclude, str.length());
    }

    /**
     * 返回作为此字符串的子字符串的字符串。子字符串从指定的beginIndex开始并延伸到索引endIndex - 1处的字符。
     * 因此子字符串的长度是endIndex-beginIndex
     *
     * @param str              str
     * @param fromIndexInclude 起始索引（包含）
     * @param toIndexExclude   结束索引（不包含）
     * @return {@link String }
     */
    static String read(String str, int fromIndexInclude, int toIndexExclude) {
        if (str == null) {
            return "";
        }

        int len = str.length();

        if (fromIndexInclude < 0) {
            fromIndexInclude = len + fromIndexInclude;
            if (fromIndexInclude < 0) {
                fromIndexInclude = 0;
            }
        } else if (fromIndexInclude > len) {
            fromIndexInclude = len;
        }

        if (toIndexExclude < 0) {
            toIndexExclude = len + toIndexExclude;
            if (toIndexExclude < 0) {
                toIndexExclude = len;
            }
        } else if (toIndexExclude > len) {
            toIndexExclude = len;
        }

        if (toIndexExclude < fromIndexInclude) {
            int tmp = fromIndexInclude;
            fromIndexInclude = toIndexExclude;
            toIndexExclude = tmp;
        }

        if (fromIndexInclude == toIndexExclude) {
            return EMPTY;
        }

        return str.substring(fromIndexInclude, toIndexExclude);
    }
}