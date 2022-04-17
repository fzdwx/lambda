package io.github.fzdwx.lambada;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/17 13:36
 */
class CollTest {

    @Test
    void testMapConcat() {
        final Map<Object, Object> concat = Coll.concat(null, null);
        assertNotNull(concat);

        final Map<Integer, Integer> m1 = Coll.map(1, 2, 3, 4, 5, 6);
        final Map<Integer, Integer> m2 = Coll.map(7, 8, 9, 10);
        final Map<Integer, Integer> m = Coll.concat(m1, m2);
        assertEquals(5, m.size());


        final Map<Integer, Integer> m4 = Coll.map(1, 2, 3, 4, 5, 6);
        final Map<Integer, Integer> m3 = Coll.map(7, 8, 9, 10);
        final Map<Integer, Integer> m5 = Coll.concat(m3, m4);
        assertEquals(5, m5.size());

        assertEquals(3, Coll.concat(Coll.map(1,1,2,3,4,4), null).size());
        assertEquals(2, Coll.concat( null,Coll.map(1,1,2,2)).size());
    }
}