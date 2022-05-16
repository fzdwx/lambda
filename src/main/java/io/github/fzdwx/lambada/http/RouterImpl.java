package io.github.fzdwx.lambada.http;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import io.github.fzdwx.lambada.Collections;
import io.github.fzdwx.lambada.Lang;
import io.github.fzdwx.lambada.Tuple;
import io.github.fzdwx.lambada.internal.Tuple2;
import io.github.fzdwx.lambada.lang.NvMap;
import io.github.fzdwx.lambada.anno.NonNull;
import io.github.fzdwx.lambada.anno.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * router implementation
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/5 16:29
 */
public class RouterImpl<Handler> implements Router<Handler> {

    private final Map<HttpMethod, Route> routes;

    private final Map<String, Handler> handlers;

    public RouterImpl() {
        this.routes = Collections.map();
        this.handlers = Collections.map();
    }

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
    public Router<Handler> addRoute(final HttpMethod method, final String pattern, final Handler handler) {
        final String[] parts = toParts(pattern);

        final String key = method + " - " + pattern;
        final Route route = routes.get(method);
        if (route == null) {
            routes.put(method, new Route());
        }

        routes.get(method).insert(pattern, 0, parts);
        handlers.put(key, handler);
        return this;
    }

    /**
     * 匹配路由
     *
     * @param method 方法
     * @param path   路径
     * @return {@link Tuple2 }<{@link Handler }, {@link Map }<{@link String }, {@link String }>>
     */
    public Tuple2<Handler, NvMap> match(final HttpMethod method, final String path) {
        final String[] searchParts = toParts(path);
        final NvMap params = NvMap.create();
        Handler handler = null;

        final Route route = routes.get(method).search(0, searchParts);

        if (route != null) {
            String[] parts = toParts(route.pattern);
            for (int i = 0; i < parts.length; i++) {
                final String part = parts[i];
                if (part.charAt(0) == ':') {
                    params.put(part.substring(1), searchParts[i]);
                }
                if (part.charAt(0) == '*' && part.length() > 1) {
                    params.put(part.substring(1), ArrayUtil.join(ArrayUtil.sub(searchParts, i, searchParts.length), "/"));
                    break;
                }
            }

            String key = method + " - " + route.pattern;
            handler = handlers.get(key);
        }

        return Tuple.of(handler, params);
    }

    @Override
    public Map<String, Handler> handlers() {
        return this.handlers;
    }

    public static class Route {

        /**
         * 路由
         * e.g: /hello/world
         */
        private String pattern;

        /**
         * pattern中的一部分
         * e.g. world
         */
        private String part;

        /**
         * 子节点
         */
        private List<Route> children;

        /**
         * 是否为精确匹配,有:或*时为true
         */
        private boolean wildFlag;

        public Route(final String part) {
            this.part = part;
            this.wildFlag = part.charAt(0) == ':' || part.charAt(0) == '*';
            this.children = Collections.list();
        }

        public Route() {
            this.wildFlag = false;
            this.children = Collections.list();
        }


        /**
         * @throws IllegalArgumentException when parts is null
         */
        public void insert(String pattern, int height, String... parts) throws IllegalArgumentException {

            if (Lang.eq(parts.length, height)) {
                this.pattern = pattern;
                return;
            }

            final String part = parts[height];
            Route child = this.matchChild(part);
            if (child == null) {
                child = new Route(part);
                this.children.add(child);
            }

            child.insert(pattern, height + 1, parts);
        }

        public Route search(int height, String... parts) {
            if (Lang.eq(parts.length, height) || StrUtil.startWith(this.part, "*")) {
                if ("".equals(this.pattern)) {
                    return null;
                }

                return this;
            }

            final String part = parts[height];
            final List<Route> children = this.matchChildren(part);

            for (final Route child : children) {
                final Route route = child.search(height + 1, parts);
                if (route != null) {
                    return route;
                }
            }

            return null;
        }

        /**
         * 匹配所有子节点
         */
        @NonNull
        public List<Route> matchChildren(String part) {
            List<Route> routes = Collections.list();

            if (isEmpty()) {
                return routes;
            }

            for (final Route child : children) {
                if (eq(part, child)) {
                    routes.add(child);
                }
            }

            return routes;
        }

        /**
         * 匹配某个子节点
         */
        @Nullable
        public Route matchChild(String part) {
            if (isEmpty()) {
                return null;
            }

            for (Route child : children) {
                if (eq(part, child)) {
                    return child;
                }
            }

            return null;
        }

        private boolean isEmpty() {
            return children == null || children.size() == 0;
        }

        private boolean eq(String part, Route child) {
            return Lang.eq(child.part, part) || child.wildFlag;
        }

    }

    private String[] toParts(String pattern) {
        if (pattern == null) {
            return new String[0];
        }

        final String[] vs = pattern.split("/");

        final List<String> parts = Collections.list();
        for (String item : vs) {
            if (!Objects.equals(item, "")) {
                parts.add(item);
                if (item.charAt(0) == '*') {
                    break;
                }
            }
        }

        return parts.toArray(new String[0]);
    }
}