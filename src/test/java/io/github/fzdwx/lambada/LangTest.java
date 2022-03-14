package io.github.fzdwx.lambada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/14 11:51
 */
class LangTest {

    @Test
    void test_defVal() {
        assertEquals("123", Lang.defVal("123", "456"));
        assertEquals("456", Lang.defVal(null, "456"));

        final Person p1 = new Person();
        p1.name = "like";

        final Person p2 = new Person();
        p2.name = "like";


        assertEquals(p1, Lang.defVal(p1, p2));
        assertEquals(p2, Lang.defVal(null, p2));
    }

    @Test
    void test_format() {
        final String template = "hello {}";
        assertEquals(Lang.format(template, "world"), "hello world");
    }

    @Test
    void test_join() {
        assertEquals(Lang.join(",", "a", "b", "c"), "a,b,c");
        assertEquals(Lang.join(Coll.list("a", "b", "c")), "a,b,c");
        assertEquals(Lang.join(Coll.list("a", "b", "c"), ","), "a,b,c");
    }
}