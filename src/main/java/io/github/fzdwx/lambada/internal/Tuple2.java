package io.github.fzdwx.lambada.internal;

import io.github.fzdwx.lambada.Tuple;

/**
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/12 9:12
 */
public class Tuple2<V1, V2> implements Tuple {

    public final V1 v1;
    public final V2 v2;

    public Tuple2(final V1 v1, final V2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    private Tuple2() {
        this.v1 = null;
        this.v2 = null;
    }
}