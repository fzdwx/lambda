package io.github.fzdwx.lambada.cake;

import java.util.Arrays;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/11 11:01
 */
public interface RepeatConcatCake<T extends RepeatConcatCake<?> & ArrayConcatCake<?>> extends Cake<T> {

    /**
     * 复制字符串多次并连接起来
     *
     * @param tmp   String to be copied
     * @param times number of copies
     * @return {@link String }
     */
    default String repeat(String tmp, int times) {
        String[] arr = new String[times];
        Arrays.fill(arr, tmp);
        return me().concatArray(arr);
    }
}