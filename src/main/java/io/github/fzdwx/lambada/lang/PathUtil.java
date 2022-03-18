package io.github.fzdwx.lambada.lang;

import io.github.fzdwx.lambada.Lang;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.fzdwx.lambada.Lang.isEmpty;

/**
 * 路径跑龙套
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/03/18 17:02:44
 * @since 0.06
 */
public class PathUtil {

    /**
     * 合并两个路径
     */
    public static String mergePath(String path1, String path2) {
        if (isEmpty(path1) || "**".equals(path1) || "/**".equals(path1)) {
            if (path2.startsWith("/")) {
                return path2;
            } else {
                return "/" + path2;
            }
        }

        if (!path1.startsWith("/")) {
            path1 = "/" + path1;
        }

        if (isEmpty(path2)) {
            if (path1.endsWith("*")) {
                //支持多个*情况
                int idx = path1.lastIndexOf('/') + 1;
                if (idx < 1) {
                    return "/";
                } else {
                    return path1.substring(0, idx) + path2;
                }
            } else {
                return path1;
            }
        }

        if (path2.startsWith("/")) {
            path2 = path2.substring(1);
        }

        if (path1.endsWith("/")) {
            return path1 + path2;
        } else {
            if (path1.endsWith("*")) {
                //支持多个*情况
                int idx = path1.lastIndexOf('/') + 1;
                if (idx < 1) {
                    return path2;
                } else {
                    return path1.substring(0, idx) + path2;
                }
            } else {
                return path1 + "/" + path2;
            }
        }
    }

    public static final Pattern pathKeyExpr = Pattern.compile("\\{([^\\\\}]+)\\}");

    /**
     * 将路径根据表达式转成map
     */
    public static NvMap pathVarMap(String path, String expr) {
        NvMap _map = new NvMap();

        //支持path变量
        if (expr.contains("{")) {
            String path2 = null;
            try {
                path2 = URLDecoder.decode(path, Lang.CHARSET.displayName());
            } catch (Throwable ex) {
                path2 = path;
            }

            Matcher pm = pathKeyExpr.matcher(expr);

            List<String> _pks = new ArrayList<>();

            while (pm.find()) {
                _pks.add(pm.group(1));
            }

            if (_pks.size() > 0) {
                PathAnalyzer _pr = PathAnalyzer.get(expr);

                pm = _pr.matcher(path2);
                if (pm.find()) {
                    for (int i = 0, len = _pks.size(); i < len; i++) {
                        _map.put(_pks.get(i), pm.group(i + 1));//不采用group name,可解决_的问题
                    }
                }
            }
        }

        return _map;
    }
}