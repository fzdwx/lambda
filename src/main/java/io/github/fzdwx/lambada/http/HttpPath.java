package io.github.fzdwx.lambada.http;

import io.github.fzdwx.lambada.Lang;
import io.github.fzdwx.lambada.lang.StringPool;

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
        if (path == null || path.equals(StringPool.SLASH)) {
            path = Lang.EMPTY_STR;
        }

        while (path.startsWith(StringPool.DOUBLE_SLASH)) {
            path = path.substring(1);
        }

        if (!path.startsWith(StringPool.SLASH)) {
            path = StringPool.SLASH + path;
        }

        if(path.length() > 1) {
            while (path.endsWith(StringPool.SLASH)) {
                path = path.substring(0, path.length() - 1);
            }
        }

        return path;
    }
}