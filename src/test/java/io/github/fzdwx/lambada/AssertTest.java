package io.github.fzdwx.lambada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/1 17:34
 */
class AssertTest {

    @Test
    void test_gt() {
        System.out.println(Assert.gt0(1));
        System.out.println(Assert.gt(10, 0));
        System.out.println(Assert.gt(-10, 0));
    }

    @Test
    void test_1() {
        final Object o1 = new Object();
        final Object o2 = new Object();

        final boolean b = Assert.bothNullOrBothNotNull(o1, o2);
        assertFalse(b);
    }

    @Test
    void test2() {
        final Object o1 = new Object();

        final boolean b = Assert.bothNullOrBothNotNull(o1, null);
        assertTrue(b);
    }

    @Test
    void test_3() {

        final boolean b = Assert.bothNullOrBothNotNull(null, null);
        assertFalse(b);
    }

    @Test
    void test_4() {
        final Object o2 = new Object();

        final boolean b = Assert.bothNullOrBothNotNull(null, o2);
        assertTrue(b);
    }
}