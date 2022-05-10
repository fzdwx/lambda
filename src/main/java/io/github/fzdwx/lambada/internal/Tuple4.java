package io.github.fzdwx.lambada.internal;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/14 16:05
 */
public class Tuple4<V1, V2, V3, V4> extends Tuple3<V1, V2, V3> {

    public final V4 v4;

    public Tuple4(final V1 v1, final V2 v2, final V3 v3, final V4 v4) {
        super(v1, v2, v3);
        this.v4 = v4;
    }
}