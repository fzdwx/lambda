package io.github.fzdwx.lambada.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 11:08
 */
class ContentTypeTest {

    @Test
    void name() {
        ContentType.Parse.m.get("123");
    }
}