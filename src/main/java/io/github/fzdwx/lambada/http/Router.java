package io.github.fzdwx.lambada.http;

import io.github.fzdwx.lambada.anno.Nullable;
import io.github.fzdwx.lambada.lang.NvMap;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * router implementation
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/6 10:54
 */
public interface Router<Handler> {

    static <Handler> RouterImpl<Handler> router() {
        return new RouterImpl<>();
    }

    static PathPatternParser PARSER = new PathPatternParser();

    /**
     * 添加路由
     *
     * @param method  HTTP method
     * @param pattern 路由规则
     * @param handler 处理程序
     * @throws IllegalArgumentException 当httpMethod解析错误时抛出
     * @apiNote eg.<pre>
     *     <code>router.addRoute("GET", "/", "1");</code>
     *     <code>router.addRoute("GET", "/hello/:name", "2");</code>
     *     <code>router.addRoute("GET", "/assets/*filepath", "5");</code>
     * </pre>
     */
    Router<Handler> addRoute(final HttpMethod method, final String pattern, final Handler handler);

    /**
     * 匹配路由
     *
     * @param method 方法
     * @param path   路径
     * @return router handler and params map
     */
    @Nullable
    Route<Handler> match(final HttpMethod method, final String path);

    /**
     * @see #match(HttpMethod, String)
     */
    default Route<Handler> match(String method, String path) {
        final HttpMethod httpMethod = HttpMethod.of(method);

        return match(httpMethod, path);
    }

    /**
     * @see #addRoute(HttpMethod, String, Object)
     */
    default Router<Handler> addRoute(String method, String pattern, Handler handler) throws IllegalArgumentException {
        final HttpMethod httpMethod = HttpMethod.of(method);
        if (httpMethod == null) {
            throw new IllegalArgumentException("method is not supported");
        }

        addRoute(httpMethod, pattern, handler);

        return this;
    }

    /**
     * @see #addRoute(HttpMethod, String, Object)
     */
    default Router<Handler> GET(String pattern, Handler handler) {
        addRoute(HttpMethod.GET, pattern, handler);
        return this;
    }

    /**
     * @see #addRoute(HttpMethod, String, Object)
     */
    default Router<Handler> POST(String pattern, Handler handler) {
        addRoute(HttpMethod.POST, pattern, handler);
        return this;
    }

    /**
     * @see #addRoute(HttpMethod, String, Object)
     */
    default Router<Handler> PUT(String pattern, Handler handler) {
        addRoute(HttpMethod.PUT, pattern, handler);
        return this;
    }

    /**
     * @see #addRoute(HttpMethod, String, Object)
     */
    default Router<Handler> DELETE(String pattern, Handler handler) {
        addRoute(HttpMethod.DELETE, pattern, handler);
        return this;
    }

    interface Route<Handler> {

        String pattern();

        Handler handler();

        /**
         * 提取路径参数
         * <p>
         * 只支持 :或*开头的参数
         *
         * @apiNote 只能调用一次.
         */
        NvMap extract(String path);

        /**
         * 在match的时候设置
         */
        Route<Handler> setSearchParts(String[] searchParts);

        /**
         * extract的时候清除
         */
        void clearSearchParts();
    }
}