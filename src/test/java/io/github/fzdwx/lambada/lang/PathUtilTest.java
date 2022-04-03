package io.github.fzdwx.lambada.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/4/3 11:15
 */
class PathUtilTest {

    @Test
    void test1() {
        final String p1 = "/hello/{name}";
        final String p2 = "/hello/like";

        final NvMap nvMap = PathUtil.pathVarMap(p2, p1);

        Assertions.assertTrue(nvMap.containsKey("name"));
    }

    @Test
    void test_2() {
        final String p1 = "/hello/{name}";
        final String p2 = "/hello/like";
        final PathAnalyzer pathAnalyzer = PathAnalyzer.get(p1);

        Assertions.assertTrue(pathAnalyzer.matcher(p2).find());
    }
}