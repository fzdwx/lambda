package io.github.fzdwx.lambada.lang;

import io.github.fzdwx.lambada.Lang;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @see LinkedCaseInsensitiveMap
 */
public class KvMap extends LinkedCaseInsensitiveMap<String> {

    public static KvMap create() {
        return new KvMap();
    }

    public KvMap() {
        super();
    }

    public KvMap(Map<?, ?> map) {
        super();

        if (map != null) {
            map.forEach((k, v) -> {
                if (k != null && v != null) {
                    put(k.toString(), v.toString());
                }
            });
        }
    }

    public KvMap set(String key, String val) {
        put(key, val);
        return this;
    }

    public static KvMap from(String[] args) {
        return from(Arrays.asList(args));
    }

    public static KvMap from(List<String> args) {
        KvMap d = new KvMap();

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

    public KvMap add(final String key, final Iterable<String> value) {
        for (final String s : value) {
            this.put(key, s);
        }
        return this;
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
        return getLong(key, 0L);
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