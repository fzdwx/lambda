package io.github.fzdwx.lambada.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mark value maybe null.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @apiNote just mark, no effect.
 * @date 2022/5/16 14:13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER, ElementType.METHOD})
public @interface NonNull { }