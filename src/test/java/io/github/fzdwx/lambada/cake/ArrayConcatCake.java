package io.github.fzdwx.lambada.cake;

import java.util.Arrays;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/11 10:48
 */
public interface ArrayConcatCake<T extends EmptyCake<?> & ConcatCake<?> & ArrayConcatCake<?>> extends Cake<T> {

    default String concatArray(String... array) {
        return Arrays.stream(array).reduce(me()::concat).orElse(me().empty());
    }
}