package io.github.fzdwx.lambada.lang;

import io.github.fzdwx.lambada.Lang;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 可排序，不区分大小写（Name value map）
 * <p>
 * 用于：参数解析，Header，Param 处理
 *
 * @author noear
 * @since 0.06
 */
public class NvMap extends LinkedCaseInsensitiveMap<String> {

    public NvMap() {
        super();
    }

    public NvMap(Map map) {
        super();

        if (map != null) {
            map.forEach((k, v) -> {
                if (k != null && v != null) {
                    put(k.toString(), v.toString());
                }
            });
        }
    }

    public NvMap set(String key, String val) {
        put(key, val);
        return this;
    }

    public static NvMap from(String[] args) {
        return from(Arrays.asList(args));
    }

    public static NvMap from(List<String> args) {
        NvMap d = new NvMap();

        if (args != null) {
            for (String arg : args) {
                if (arg.indexOf("=") > 0) {
                    String name = arg.substring(0, arg.indexOf('='));
                    String value = arg.substring(arg.indexOf('=') + 1);
                    d.put(name.replaceAll("^-*", ""), value);
                } else {
                    d.put(arg.replaceAll("^-*", ""), "");
                }
            }
        }

        return d;
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int def) {
        String temp = get(key);
        if (Lang.isEmpty(temp)) {
            return def;
        } else {
            return Integer.parseInt(temp);
        }
    }

    public long getLong(String key) {
        return getLong(key, 0l);
    }

    public long getLong(String key, long def) {
        String temp = get(key);
        if (Lang.isEmpty(temp)) {
            return def;
        } else {
            return Long.parseLong(temp);
        }
    }

    public double getDouble(String key) {
        return getDouble(key, 0d);
    }

    public double getDouble(String key, double def) {
        String temp = get(key);
        if (Lang.isEmpty(temp)) return def;
        else {
            return Double.parseDouble(temp);
        }
    }

    public boolean getBool(String key, boolean def) {
        if (containsKey(key)) {
            return Boolean.parseBoolean(get(key));
        } else {
            return def;
        }
    }
}