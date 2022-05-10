package io.github.fzdwx.lambada.internal;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/14 16:05
 */
public class Tuple9<V1, V2, V3, V4, V5, V6, V7, V8, V9> extends Tuple8<V1, V2, V3, V4, V5, V6, V7, V8> {

    public final V9 v9;

    public Tuple9(final V1 v1, final V2 v2, final V3 v3, final V4 v4, V5 v5, V6 v6, V7 v7, V8 v8, V9 v9) {
        super(v1, v2, v3, v4, v5, v6, v7, v8);
        this.v9 = v9;
    }
}