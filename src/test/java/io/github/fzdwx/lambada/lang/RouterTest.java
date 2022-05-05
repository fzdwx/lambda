package io.github.fzdwx.lambada.lang;

import io.github.fzdwx.lambada.internal.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/5/5 17:13
 */
class RouterTest {


    @Test
    void test() {
        final Router<Object> router = Router.router();

        router.addRoute("GET", "/", "1");
        router.addRoute("GET", "/hello/:name", "2");
        router.addRoute("GET", "/hello/b/c", "3");
        router.addRoute("GET", "/hi/:name", "4");
        router.addRoute("GET", "/assets/*filepath", "5");
        router.addRoute("GET", "/hhhh", "6");


        Assertions.assertEquals(router.getRoute("GET", "/hhhh").v1, "6");
        Assertions.assertEquals(router.getRoute("GET", "/").v1, "1");
        final Tuple2<Object, Map<String, String>> hi = router.getRoute("GET", "/hi/123");
        Assertions.assertEquals(hi.v1, "4");
        Assertions.assertEquals(hi.v2.get("name"), "123");
        final Tuple2<Object, Map<String, String>> assets = router.getRoute("GET", "/assets/123filepath");
        Assertions.assertEquals(assets.v1, "5");
        Assertions.assertEquals(assets.v2.get("filepath"), "123filepath");
        Assertions.assertNull(router.getRoute("GET", "/hhhh1").v1);
    }
}