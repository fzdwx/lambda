package io.github.fzdwx.lambada.http;

import io.github.fzdwx.lambada.lang.KvMap;

public interface Route<Handler> {

    String pattern();

    Handler handler();

    /**
     * extract path parameters.
     */
    KvMap extract(final String path);
}