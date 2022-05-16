package io.github.fzdwx.lambada;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/17 13:36
 */
class CollectionsTest {

    @Test
    void testMapConcat() {
        final Map<Object, Object> concat = Collections.concat(null, null);
        assertNotNull(concat);

        final Map<Integer, Integer> m1 = Collections.map(1, 2, 3, 4, 5, 6);
        final Map<Integer, Integer> m2 = Collections.map(7, 8, 9, 10);
        final Map<Integer, Integer> m = Collections.concat(m1, m2);
        assertEquals(5, m.size());


        final Map<Integer, Integer> m4 = Collections.map(1, 2, 3, 4, 5, 6);
        final Map<Integer, Integer> m3 = Collections.map(7, 8, 9, 10);
        final Map<Integer, Integer> m5 = Collections.concat(m3, m4);
        assertEquals(5, m5.size());

        assertEquals(3, Collections.concat(Collections.map(1,1,2,3,4,4), null).size());
        assertEquals(2, Collections.concat( null, Collections.map(1,1,2,2)).size());
    }
}