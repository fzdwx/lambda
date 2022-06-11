package io.github.fzdwx.lambada.cake;

import io.github.fzdwx.lambada.lang.StrPool;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/11 10:39
 */
public interface EmptyCake<T extends EmptyCake<?>> extends Cake<T> {

    /**
     * return {@link StrPool#EMPTY}
     */
    default String empty() {
        return StrPool.EMPTY;
    }
}