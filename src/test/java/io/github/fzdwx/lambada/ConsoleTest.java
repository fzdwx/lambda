package io.github.fzdwx.lambada;

import org.jline.reader.LineReader;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/5/14 17:31
 */
class ConsoleTest {

    public static void main(String[] args) {
        final LineReader lineReader = Console.defaultLineReader();
        for (; ; ) {
            final String line = lineReader.readLine("> ");
            if (Lang.eq("where", line)) {
                System.out.println(Console.where());
            }
        }
    }

    @Test
    void test_2() {
        Console.print(Console.green("hello") + Console.red("world"));
    }

    @Test
    void test_1() {
        Console.greenln("hello world");
        Seq.range(100).forEach(i -> {
            Threads.sleep(Duration.ofMillis(100));
            Console.printProgress('#', i);
        });
    }
}