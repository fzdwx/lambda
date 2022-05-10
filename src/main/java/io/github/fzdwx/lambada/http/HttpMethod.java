package io.github.fzdwx.lambada.http;

import java.util.Locale;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/6 10:40
 */
public enum HttpMethod {

    GET, POST, PUT, PATCH,DELETE, HEAD, OPTIONS, TRACE, CONNECT;

    /**
     * Returns the {@link HttpMethod} represented by the specified name.
     * If the specified name is a standard HTTP method name, a cached instance
     * will be returned.  Otherwise, a new instance will be returned.
     */
    public static HttpMethod of(String method) throws IllegalArgumentException {
        method = method.toUpperCase(Locale.ROOT);
        return valueOf(method);
    }

}