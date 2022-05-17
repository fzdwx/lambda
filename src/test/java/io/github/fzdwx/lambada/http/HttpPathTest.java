package io.github.fzdwx.lambada.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 12:08
 */
class HttpPathTest {

    @Test
    void test_1() {
        System.out.println(HttpPath.format("/hello"));
        System.out.println(HttpPath.format("/hello/"));
        System.out.println(HttpPath.format("hello"));
    }
}