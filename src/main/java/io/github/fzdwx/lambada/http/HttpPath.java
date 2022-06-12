package io.github.fzdwx.lambada.http;

import io.github.fzdwx.lambada.Lang;
import io.github.fzdwx.lambada.lang.StrPool;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 12:02
 */
public interface HttpPath {

    /**
     * format path
     *
     * @apiNote <pre>
     * /hello  -> /hello
     * /hello/ -> /hello
     * hello   -> /hello
     * </pre>
     */
    static String format(String path) {
        if (path == null || path.equals(StrPool.SLASH)) {
            path = Lang.EMPTY_STR;
        }

        while (path.startsWith(StrPool.DOUBLE_SLASH)) {
            path = path.substring(1);
        }

        if (!path.startsWith(StrPool.SLASH)) {
            path = StrPool.SLASH + path;
        }

        if(path.length() > 1) {
            while (path.endsWith(StrPool.SLASH)) {
                path = path.substring(0, path.length() - 1);
            }
        }

        return path;
    }
}