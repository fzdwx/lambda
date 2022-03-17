package io.github.fzdwx.lambada.lang;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/17 14:18
 */
class UnixTimeTest {

    @Test
    void test_of() {
        assertEquals(new Date(UnixTime.unixTime()).toString(), UnixTime.now().toDate().toString());

        assertEquals(new Date(1647498067000L), UnixTime.of(1647498067000L).toDate());
    }
}