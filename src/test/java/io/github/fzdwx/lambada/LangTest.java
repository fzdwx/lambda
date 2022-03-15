package io.github.fzdwx.lambada;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void test_seq() {
        final Seq<Integer> seq = Seq.of(1).concat(2).concat(3);
        seq.println();
    }

    @Test
    void test_qwe() {
        final List<Tag> list = Coll.list(
                Tag.of(1, 0, "a"),
                Tag.of(2, 0, "b"),
                Tag.of(3, 0, "c"),
                Tag.of(4, 1, "d"),
                Tag.of(5, 1, "e"),
                Tag.of(6, 2, "f"),
                Tag.of(7, 2, "g"),
                Tag.of(8, 3, "z"),
                Tag.of(9, 3, "y"),
                Tag.of(10, 1, "l"),
                Tag.of(11, 2, "p"),
                Tag.of(12, 3, "m"),
                Tag.of(13, 3, "O"),
                Tag.of(14, 3, "OU")
        );
        final List<Tree<Integer>> build = TreeUtil.build(list, 0, (object, treeNode) -> {
            treeNode.setId(object.getId());
            treeNode.setName(object.getName());
            treeNode.setParentId(object.getPid());
        });
        System.out.println(build);
    }

    @Data
    static class Tag {

        private String name;
        private Integer id;
        private Integer pid;

        public static Tag of(Integer id, Integer pid, String name) {
            final Tag tag = new Tag();
            tag.name = name;
            tag.id = id;
            tag.pid = pid;
            return tag;
        }
    }
}