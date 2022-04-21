package io.github.fzdwx.lambada;

import lombok.NonNull;

import java.io.ByteArrayInputStream;

/**
 * io util.
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/4/21 15:04
 */
public interface Io {

    /**
     * 包装 字节数组到 字节输入流
     *
     * @param bytes 字节数组
     * @return {@link ByteArrayInputStream }
     * @apiNote byte[] -> ByteArrayInputStream,bytes not null.
     */
    static ByteArrayInputStream wrap(byte @NonNull [] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}