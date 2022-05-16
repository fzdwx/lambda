package io.github.fzdwx.lambada.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * just mark, no effect.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @apiNote mark value can be null.
 * @date 2022/5/16 14:13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE_USE,
        ElementType.TYPE_PARAMETER,
        ElementType.METHOD
})
public @interface Nullable {
}