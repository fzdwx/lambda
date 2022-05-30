package io.github.fzdwx.lambada.http;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.fzdwx.lambada.Lang;
import io.github.fzdwx.lambada.Seq;

import java.util.Map;

class ContentParseParse {

    static Map<String, String> m;

    static {
        final String s = FileUtil.readString("mime.json", Lang.CHARSET);
        final JSONArray array = JSONUtil.parseObj(s).getJSONArray("mime-mapping");
        m = Seq.toMap(array.stream(), o1 -> ((JSONObject) o1).getStr("extension"), o2 -> ((JSONObject) o2).getStr("mime-type"));
    }
}