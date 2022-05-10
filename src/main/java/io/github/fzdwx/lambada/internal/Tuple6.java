package io.github.fzdwx.lambada.internal;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/14 16:05
 */
public class Tuple6<V1, V2, V3, V4, V5, V6> extends Tuple5<V1, V2, V3, V4, V5> {

    public final V6 v6;

    public Tuple6(final V1 v1, final V2 v2, final V3 v3, final V4 v4, V5 v5, V6 v6) {
        super(v1, v2, v3, v4, v5);
        this.v6 = v6;
    }
}