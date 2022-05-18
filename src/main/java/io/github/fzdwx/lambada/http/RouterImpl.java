package io.github.fzdwx.lambada.http;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import io.github.fzdwx.lambada.Collections;
import io.github.fzdwx.lambada.Lang;
import io.github.fzdwx.lambada.anno.NonNull;
import io.github.fzdwx.lambada.anno.Nullable;
import io.github.fzdwx.lambada.lang.NvMap;

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

    private final Map<HttpMethod, RouteImpl> routes;

    public RouterImpl() {
        this.routes = Collections.map();
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

        final RouteImpl routeImpl = routes.get(method);
        if (routeImpl == null) {
            routes.put(method, new RouteImpl());
        }

        routes.get(method).insert(pattern, 0, handler, parts);
        return this;
    }

    /**
     * 匹配路由
     *
     * @param method HttpMethod
     * @param path   path
     * @return Handler
     */
    public Route<Handler> match(final HttpMethod method, final String path) {
        final String[] searchParts = toParts(path);
        return routes.get(method).search(0, searchParts);
    }

    public class RouteImpl implements Router.Route<Handler> {

        /**
         * 路由
         * e.g: /hello/world
         */
        private String pattern;

        private String[] patternParts;

        /**
         * pattern中的一部分
         * e.g. world
         */
        private String part;

        /**
         * 子路由
         */
        private final List<RouteImpl> children;

        /**
         * 是否为精确匹配,有:或*时为true
         *
         * @apiNote <pre>
         *     1. startWith :
         *     2. startWith *
         *     3. startWith {  &&  endWith }
         * </pre>
         */
        private final boolean wildFlag;
        private Handler handler;

        public RouteImpl(final String part) {
            this.part = part;
            if (springType(part)) {
                this.wildFlag = true;
            } else {
                this.wildFlag = part.charAt(0) == ':' || part.charAt(0) == '*';
            }
            this.children = Collections.list();
        }

        public RouteImpl() {
            this.wildFlag = false;
            this.children = Collections.list();
        }

        /**
         * @throws IllegalArgumentException when parts is null
         */
        public void insert(String pattern, int height, final Handler handler, String... parts) throws IllegalArgumentException {

            if (Lang.eq(parts.length, height)) { // init parent.
                this.pattern = pattern;
                this.handler = handler;
                this.patternParts = toParts(pattern);
                return;
            }

            final String part = parts[height];
            RouteImpl child = this.matchChild(part);
            if (child == null) {
                child = new RouteImpl(part);
                this.children.add(child);
            }

            child.insert(pattern, height + 1, handler, parts);
        }

        public RouteImpl search(final int height, String... parts) {
            if (Lang.eq(parts.length, height) || StrUtil.startWith(this.part, "*")) {
                if ("".equals(this.pattern)) {
                    return null;
                }

                return this;
            }

            final String part = parts[height];
            final List<RouteImpl> children = this.matchChildren(part);

            for (final RouteImpl child : children) {
                final RouteImpl routeImpl = child.search(height + 1, parts);
                if (routeImpl != null) {
                    return routeImpl;
                }
            }

            return null;
        }

        /**
         * 匹配所有子节点
         */
        @NonNull
        public List<RouteImpl> matchChildren(String part) {
            List<RouteImpl> routeImpls = Collections.list();

            if (isEmpty()) {
                return routeImpls;
            }

            for (final RouteImpl child : children) {
                if (eq(part, child)) {
                    routeImpls.add(child);
                }
            }

            return routeImpls;
        }

        /**
         * 匹配某个子节点
         */
        @Nullable
        public RouteImpl matchChild(String part) {
            if (isEmpty()) {
                return null;
            }

            for (RouteImpl child : children) {
                if (eq(part, child)) {
                    return child;
                }
            }

            return null;
        }

        @Override
        public String pattern() {
            return this.pattern;
        }

        @Override
        public Handler handler() {
            return handler;
        }

        @Override
        public NvMap extract(final String path) {
            final NvMap nvMap = NvMap.create();

            if (path == null) {
                return nvMap;
            }

            final String[] searchParts = toParts(path);

            String[] parts = this.patternParts;
            for (int i = 0; i < parts.length; i++) {
                final String part = parts[i];
                if (part.charAt(0) == ':') {
                    nvMap.put(part.substring(1), searchParts[i]);
                }

                if (part.charAt(0) == '*' && part.length() > 1) {
                    nvMap.put(part.substring(1), ArrayUtil.join(ArrayUtil.sub(searchParts, i, searchParts.length), "/"));
                    break;
                }

                if (springType(part)) {
                    nvMap.put(part.substring(1, part.length() - 1), searchParts[i]);
                }
            }
            return nvMap;
        }

        private boolean isEmpty() {
            return children == null || children.size() == 0;
        }

        private boolean eq(String part, RouteImpl child) {
            return Lang.eq(child.part, part) || child.wildFlag;
        }

    }

    private boolean springType(final String part) {
        return part.charAt(0) == '{' && part.charAt(part.length() - 1) == '}';
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