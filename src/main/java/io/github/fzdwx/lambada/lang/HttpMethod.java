package io.github.fzdwx.lambada.lang;

import java.util.Locale;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/6 10:40
 */
public enum HttpMethod {

    GET, POST, PUT, DELETE, HEAD, OPTIONS, TRACE, CONNECT;

    /**
     * @throws IllegalArgumentException 当参数不是一个合法的HTTP方法时抛出
     */
    public static HttpMethod of(String method) throws IllegalArgumentException {
        method = method.toUpperCase(Locale.ROOT);
        return valueOf(method);
    }

}