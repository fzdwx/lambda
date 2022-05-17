package io.github.fzdwx.lambada.http;

import cn.hutool.core.codec.PercentCodec;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 11:39
 */
public class UrlEncode {

    public static PercentCodec DEFAULT;

    static {
        DEFAULT = PercentCodec.of(
                "-" +
                        "." +
                        "_" +
                        "~" +
                        "!" +
                        "$" +
                        "&" +
                        "\\" +
                        "(" +
                        ")" +
                        "*" +
                        "+" +
                        "," +
                        ";" +
                        "=" +
                        ":" +
                        "@" +
                        "/" +
                        "*" +
                        "-" +
                        "." +
                        "_" +
                        "=" +
                        "&"
        );
    }
}