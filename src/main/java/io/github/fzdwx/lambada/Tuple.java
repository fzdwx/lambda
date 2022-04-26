package io.github.fzdwx.lambada;


import io.github.fzdwx.lambada.internal.Tuple2;
import io.github.fzdwx.lambada.internal.Tuple3;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/12 9:12
 * @since 0.01
 */
public interface Tuple {

    static <V1, V2> Tuple2<V1, V2> of(final V1 v1, final V2 v2) {
        return new Tuple2<>(v1, v2);
    }

    static <V1, V2, V3> Tuple3<V1, V2, V3> of(final V1 v1, final V2 v2, final V3 v3) {
        return new Tuple3<>(v1, v2, v3);
    }
}