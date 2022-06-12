package io.github.fzdwx.lambada.cake;

import cn.hutool.core.util.StrUtil;
import io.github.fzdwx.lambada.lang.StrPool;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/11 10:40
 */
public interface ConcatCake<T extends ConcatCake<?>> extends Cake<T> {

    /**
     * concat string(null will to {@link io.github.fzdwx.lambada.lang.StrPool#EMPTY})
     *
     * @param first  first string
     * @param second second string
     * @return concatenated string
     */
    default String concat(String first, String second) {
        return StrUtil.concat(true, first, joiner(), second);
    }

    default String joiner() {
        return StrPool.SPACE;
    }
}