package io.github.fzdwx.lambada;

import io.github.fzdwx.lambada.anno.NonNull;
import io.github.fzdwx.lambada.anno.Nullable;
import io.github.fzdwx.lambada.lang.StringPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * io util.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/21 15:04
 */
public interface Io {

    int DEFAULT_SIZE = 4096;

    /**
     * wrap bytes to inputStream.
     *
     * @return {@link ByteArrayInputStream }
     * @apiNote byte[] -> ByteArrayInputStream,bytes not null.
     */
    static ByteArrayInputStream wrap(@NonNull byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * wrap string to outputStream.
     *
     * @throws IOException if an I/O error occurs
     * @apiNote charset default UTF-8.
     */
    static OutputStream wrapToOut(String in, Charset charset) throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();

        transform(in, charset, bos);
        return bos;
    }

    /**
     * read inputStream to bytes.
     *
     * @throws IOException if an I/O error occurs
     */
    static byte[] toBytes(@Nullable InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        }

        final ByteArrayOutputStream out = new ByteArrayOutputStream(DEFAULT_SIZE);
        transform(in, out);
        return out.toByteArray();
    }

    /**
     * read inputStream to string.
     *
     * @throws IOException if an I/O error occurs
     */
    static String toString(@Nullable InputStream in, @NonNull Charset charset) throws IOException {
        if (in == null) {
            return StringPool.EMPTY;
        }
        StringBuilder out = new StringBuilder(DEFAULT_SIZE);
        InputStreamReader reader = new InputStreamReader(in, charset);
        char[] buffer = new char[DEFAULT_SIZE];
        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            out.append(buffer, 0, charsRead);
        }
        return out.toString();
    }

    /**
     * copy outputStream to string.
     */
    static String toString(ByteArrayOutputStream baos, Charset charset) {
        Assert.nonNull(baos, "No ByteArrayOutputStream specified");
        Assert.nonNull(charset, "No Charset specified");
        try {
            // Can be replaced with toString(Charset) call in Java 10+
            return baos.toString(charset.name());
        } catch (UnsupportedEncodingException ex) {
            // Should never happen
            throw new IllegalArgumentException("Invalid charset name: " + charset, ex);
        }
    }

    /**
     * read string write to outputStream.
     *
     * @throws IOException if an I/O error occurs
     */
    static void transform(@NonNull String in, @NonNull Charset charset, @NonNull OutputStream out) throws IOException {
        Assert.nonNull(in, "input stream not found.");
        Assert.nonNull(out, "output stream not found.");
        Assert.nonNull(charset, "charset not found.");
        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(in);
        writer.flush();
    }

    /**
     * transfer inputStream to outputStream.
     *
     * @throws IOException if an I/O error occurs
     */
    static int transform(@NonNull InputStream in, @NonNull OutputStream out) throws IOException {
        Assert.nonNull(in, "input stream not found.");
        Assert.nonNull(out, "output stream not found.");

        int byteCount = 0;
        byte[] buffer = new byte[DEFAULT_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }

    /**
     * bytes to string.
     */
    static String toString(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * @see #read(String, int, int)
     */
    static String read(String str, int length) {
        return read(str, 0, length);
    }

    /**
     * @see #read(String, int, int)
     */
    static String readSuf(String str, int startIndexInclude) {
        return read(str, startIndexInclude, str.length());
    }

    /**
     * 返回作为此字符串的子字符串的字符串。子字符串从指定的beginIndex开始并延伸到索引endIndex - 1处的字符。
     * 因此子字符串的长度是endIndex-beginIndex
     *
     * @param str              str
     * @param fromIndexInclude 起始索引（包含）
     * @param toIndexExclude   结束索引（不包含）
     * @return {@link String }
     */
    static String read(String str, int fromIndexInclude, int toIndexExclude) {
        if (str == null) {
            return "";
        }

        int len = str.length();

        if (fromIndexInclude < 0) {
            fromIndexInclude = len + fromIndexInclude;
            if (fromIndexInclude < 0) {
                fromIndexInclude = 0;
            }
        } else if (fromIndexInclude > len) {
            fromIndexInclude = len;
        }

        if (toIndexExclude < 0) {
            toIndexExclude = len + toIndexExclude;
            if (toIndexExclude < 0) {
                toIndexExclude = len;
            }
        } else if (toIndexExclude > len) {
            toIndexExclude = len;
        }

        if (toIndexExclude < fromIndexInclude) {
            int tmp = fromIndexInclude;
            fromIndexInclude = toIndexExclude;
            toIndexExclude = tmp;
        }

        if (fromIndexInclude == toIndexExclude) {
            return StringPool.EMPTY;
        }

        return str.substring(fromIndexInclude, toIndexExclude);
    }

    /**
     * new randomAccessFile. when File not found, then return null.
     *
     * @param filePath filePath
     * @return {@link RandomAccessFile }
     */
    @Nullable
    static RandomAccessFile newRaf(String filePath) {
        try {
            return new RandomAccessFile(filePath, "r");
        } catch (Exception e) {
            return null;
        }
    }
}