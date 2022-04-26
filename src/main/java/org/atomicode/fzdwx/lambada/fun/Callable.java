package org.atomicode.fzdwx.lambada.fun;

/**
 * Callable.
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @apiNote 不抛出异常
 * @date 2022/3/14 14:21
 * @since 0.04
 */
public interface Callable<T> {

    T call();
}