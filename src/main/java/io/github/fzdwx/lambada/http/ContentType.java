package io.github.fzdwx.lambada.http;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import io.github.fzdwx.lambada.Lang;
import io.github.fzdwx.lambada.Seq;
import io.github.fzdwx.lambada.anno.Nullable;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * http content type
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/05/07 18:02:55
 */
public enum ContentType {

    ALL("*/*"),

    /**
     * 标准表单编码，当action为get时候，浏览器用x-www-form-urlencoded的编码方式把form数据转换成一个字串（name1=value1&amp;name2=value2…）
     */
    FORM_URLENCODED("application/x-www-form-urlencoded"),

    /**
     * Standard form encoding. When the action is get, the browser uses the x-www-form-urlencoded encoding method to convert the form data into a string (name 1=value 1&name 2=value 2...)
     */
    FORM_URLENCODED_UTF_8("application/x-www-form-urlencoded" + charset.UTF_8),

    /**
     * file upload
     */
    MULTIPART("multipart/form-data"),

    /**
     * file upload with utf-8 charset
     */
    MULTIPART_UTF_8("multipart/form-data" + charset.UTF_8),

    /**
     * json data
     */
    JSON("application/json"),

    /**
     * json data with utf-8 charset
     */
    JSON_UTF_8("application/json" + charset.UTF_8),

    /**
     * xml data
     */
    XML("application/xml"),

    /**
     * xml data with utf-8 charset
     */
    XML_UTF_8("application/xml" + charset.UTF_8),

    /**
     * pdf
     */
    PDF("application/pdf"),

    /**
     * word
     */
    DOC("application/msword"),

    /**
     * word
     */
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),

    /**
     * byte
     */
    OCTET_STREAM("application/octet-stream"),

    /**
     * form table
     */
    FROM_DATA("multipart/form-data"),

    /**
     * Plain Text
     */
    TEXT_PLAIN("text/plain"),

    /**
     * Plain Text  with utf-8 charset
     */
    TEXT_PLAIN_UTF_8("text/plain" + charset.UTF_8),

    /**
     * xml file
     */
    TEXT_XML("text/xml"),

    /**
     * xml file with utf-8 charset
     */
    TEXT_XML_UTF_8("text/xml" + charset.UTF_8),

    /**
     * html file
     */
    TEXT_HTML("text/html"),

    /**
     * html file with utf-8 charset
     */
    TEXT_HTML_UTF_8("text/html" + charset.UTF_8),

    /**
     * css file
     */
    CSS("text/css"),

    /**
     * csv file
     */
    CSV("text/csv"),

    /**
     * jpeg image
     */
    IMAGE_JPEG("image/jpeg"),

    /**
     * gif image
     */
    IMAGE_GIF("image/gif"),

    /**
     * png image
     */
    IMAGE_PNG("image/png"),

    /**
     * bmp image
     */
    IMAGE_BMP("image/bmp"),

    /**
     * webp image
     */
    IMAGE_WEBP("image/webp"),

    /**
     * svg image
     */
    IMAGE_SVG("image/svg+xml"),

    /**
     * tiff image
     */
    IMAGE_TIFF("image/tiff"),

    /**
     * icon
     */
    ICO("image/vnd.microsoft.icon"),

    /**
     * mp4
     */
    AUDIO_MP4("audio/mp4"),

    /**
     * mp3
     */
    MP3("audio/mpeg"),

    /**
     * mpeg
     */
    MPEG("video/mpeg"),

    /**
     * rich text format
     */
    RTF("application/rtf"),

    /**
     * aac
     */
    AAC("audio/aac"),

    /**
     * avi
     */
    AVI("video/x-msvideo"),

    /**
     * data stream in json format
     *
     * @apiNote can send data piece by piece
     */
    STREAM_JSON("application/stream+json"),

    /**
     * with utf_8 charset
     *
     * @see #STREAM_JSON
     */
    STREAM_JSON_UTF_8("application/stream+json" + charset.UTF_8),

    /**
     * event stream
     *
     * @apiNote can send data piece by piece
     */
    EVENT_STREAM("text/event-stream"),

    ;


    public final String value;

    /**
     * 构造
     *
     * @param value ContentType值
     */
    ContentType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    interface charset {

        String UTF_8 = "; charset=utf-8";
    }

    public String addEncode(Charset charset) {
        return value + "; charset=" + charset.name();
    }

    /**
     * 如果 MIME 类型未知，则返回null
     */
    @Nullable
    public static String parseFromFileName(String file) {
        if (file == null) {
            return null;
        }
        int period = file.lastIndexOf('.');
        if (period < 0) {
            return null;
        }
        String extension = file.substring(period + 1);
        if (extension.length() < 1) {
            return null;
        }
        return parse(extension);
    }

    @Nullable
    public static String parse(String ext) {
        if (ext == null || ext.isEmpty()) return null;
        return Parse.m.get(ext);
    }

    static class Parse {

        static Map<String, String> m;

        static {
            final String s = FileUtil.readString("mime.json", Lang.CHARSET);
            final List<JSONArray> list = JSONUtil.toBean(s, List.class);
            m = Seq.toMap(list, o1 -> o1.getStr(0), o2 -> o2.getStr(1));
        }
    }

}