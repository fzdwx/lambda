package org.atomicode.fzdwx.lambada.fun;

/**
 * result.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/3/20 16:17
 */
public interface Result<Element> {

    void then(Hooks<Element> hooks);

    static <Element> Result<Element> of(Element element) {
        return (h) -> {

        };
    }
}