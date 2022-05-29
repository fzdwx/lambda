package io.github.fzdwx.lambada;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/14 16:38
 */
public class TimeTest {

    @Test
    void test_1() {
        Seq.range(1000000)
                .mapToLong(i -> Time.now())
                .println();
    }
}