package io.github.fzdwx.lambada;


import io.github.fzdwx.lambada.internal.Tuple2;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/12 9:12
 */
public interface Tuple {

    static <V1, V2> Tuple2<V1, V2> of(final V1 v1, final V2 v2) {
        return new Tuple2<>(v1, v2);
    }
}