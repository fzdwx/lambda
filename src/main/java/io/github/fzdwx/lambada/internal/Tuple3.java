package io.github.fzdwx.lambada.internal;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/14 16:05
 */
public class Tuple3<V1, V2, V3> extends Tuple2<V1, V2> {

    public final V3 v3;

    public Tuple3(final V1 v1, final V2 v2, final V3 v3) {
        super(v1, v2);
        this.v3 = v3;
    }
}