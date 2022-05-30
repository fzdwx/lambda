package io.github.fzdwx.lambada.http;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * http method enum.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/6 10:40
 */
public enum HttpMethod {

    GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS, TRACE, CONNECT;

    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name().toUpperCase(Locale.ROOT), httpMethod);
        }
    }

    /**
     * Returns the {@link HttpMethod} represented by the specified name.
     * If the specified name is a standard HTTP method name, a cached instance
     * will be returned.
     */
    public static HttpMethod of(String method) throws IllegalArgumentException {
        method = method.toUpperCase(Locale.ROOT);
        return mappings.get(method);
    }

    /**
     * Determine whether this {@link HttpMethod} matches the given method value.
     *
     * @param method the HTTP method as a String
     * @return {@code true} if it matches, {@code false} otherwise
     */
    public boolean matches(String method) {
        if (method == null) return false;
        return name().equals(method.toUpperCase(Locale.ROOT));
    }

    /**
     * Determine whether this {@link HttpMethod} matches the given method value.
     *
     * @param method the HTTP method as a String
     * @return {@code true} if it matches, {@code false} otherwise
     */
    public boolean matches(HttpMethod method) {
        if (method == null) return false;
        return this == method;
    }

}