package io.github.fzdwx.lambada.http;

import io.github.fzdwx.lambada.lang.NvMap;

public interface Route<Handler> {

    String pattern();

    Handler handler();

    /**
     * extract path parameters.
     */
    NvMap extract(final String path);
}