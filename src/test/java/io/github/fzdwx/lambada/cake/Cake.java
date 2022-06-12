package io.github.fzdwx.lambada.cake;

/**
 * cake pattern.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/11 10:38
 */
public interface Cake<T extends Cake<?>> {

    /**
     * get this
     *
     * @return {@link T }
     */
    T me();
}