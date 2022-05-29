package io.github.fzdwx.lambada.lang;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/12 17:15
 */
public class LineTest {

    public static void main(String[] args) {
        test_1();
    }

    static void test_1() {
        final LineReader reader = LineReaderBuilder.builder().build();
        while (true) {
            final String s = reader.readLine("fzdwx> ");
            System.out.println(s);
        }
    }
}