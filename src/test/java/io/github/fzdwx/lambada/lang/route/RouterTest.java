package io.github.fzdwx.lambada.lang.route;

import io.github.fzdwx.lambada.http.Router;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
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


        Assertions.assertEquals(router.match("GET", "/hhhh").handler(), "6");
        Assertions.assertEquals(router.match("GET", "/").handler(), "1");
        final Router.Route<Object> assets = router.match("GET", "/assets/123filepath");
        Assertions.assertEquals(assets.handler(), "5");
        Assertions.assertNull(router.match("GET", "/hhhh1"));
    }
}