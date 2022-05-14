package io.github.fzdwx.lambada;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.ConsoleTable;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Console util.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/03/17 11:46:31
 */
public interface Console {

    static String banner() {
        String banner = "\n";
        banner += "    ____          __             \n";
        banner += "   / __/___  ____/ /      __" + Console.ANSI_CYAN + "_  __" + Console.ANSI_RESET + "\n";
        banner += "  / /_/_  / /" + Console.ANSI_RED + " __" + Console.ANSI_RESET + "  / | /| / " + Console.ANSI_CYAN + "/ |/_/" + Console.ANSI_RESET + "\n";
        banner += " / __/ / /_/ " + Console.ANSI_RED + "/_/" + Console.ANSI_RESET + " /| |/ |/ /" + Console.ANSI_CYAN + ">  <  " + Console.ANSI_RESET + "\n";
        banner += "/_/   /___/\\__,_/ |__/|__" + Console.ANSI_CYAN + "/_/|_|  " + Console.ANSI_RESET + " ::https://github.com/fzdwx:: " + "\n";

        return banner;
    }

    String ANSI_RESET = "\u001B[0m";
    String ANSI_BLACK = "\u001B[30m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_WHITE = "\u001B[37m";
    String TEMPLATE_VAR = "{}";

    /**
     * @return {@link LineReader }
     * @see #lineReader()
     */
    static LineReader defaultLineReader() {
        return LineReaderBuilder.builder().build();
    }

    /**
     * @apiNote showcase<pre>{@code
     * final LineReader lineReader = Console.defaultLineReader();
     * for (; ; ) {
     *     final String line = lineReader.readLine("> ");
     *     if (Lang.eq("where", line)) {
     *         System.out.println(Console.where());
     *     }
     * }
     * }</pre>
     */
    static LineReaderBuilder lineReader() {
        return LineReaderBuilder.builder();
    }

    static void blackln(Object txt) {
        out.println(ANSI_BLACK + txt);
        out.print(ANSI_RESET);
    }

    static void redln(Object txt) {
        out.println(ANSI_RED + txt);
        out.print(ANSI_RESET);
    }

    static void blueln(Object txt) {
        out.println(ANSI_BLUE + txt);
        out.print(ANSI_RESET);
    }

    static void greenln(Object txt) {
        out.println(ANSI_GREEN + txt);
        out.print(ANSI_RESET);
    }

    static void purpleln(String txt) {
        out.println(ANSI_PURPLE + txt);
        out.print(ANSI_RESET);
    }

    static void yellowln(Object txt) {
        out.println(ANSI_YELLOW + txt);
        out.print(ANSI_RESET);
    }

    // --------------------------------------------------------------------------------- Log

    /**
     * 同 System.out.println()方法，打印控制台日志
     */
    static void log() {
        out.println();
    }

    /**
     * 同 System.out.println()方法，打印控制台日志<br>
     * 如果传入打印对象为{@link Throwable}对象，那么同时打印堆栈
     *
     * @param obj 要打印的对象
     */
    static void log(Object obj) {
        if (obj instanceof Throwable) {
            final Throwable e = (Throwable) obj;
            log(e, e.getMessage());
        } else {
            log(TEMPLATE_VAR, obj);
        }
    }

    /**
     * 同 System.out.println()方法，打印控制台日志<br>
     * 如果传入打印对象为{@link Throwable}对象，那么同时打印堆栈
     *
     * @param obj1      第一个要打印的对象
     * @param otherObjs 其它要打印的对象
     * @since 5.4.3
     */
    static void log(Object obj1, Object... otherObjs) {
        if (ArrayUtil.isEmpty(otherObjs)) {
            log(obj1);
        } else {
            log(buildTemplateSplitBySpace(otherObjs.length + 1), ArrayUtil.insert(otherObjs, 0, obj1));
        }
    }

    /**
     * 同 System.out.println()方法，打印控制台日志<br>
     * 当传入template无"{}"时，被认为非模板，直接打印多个参数以空格分隔
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void log(String template, Object... values) {
        if (ArrayUtil.isEmpty(values) || StrUtil.contains(template, TEMPLATE_VAR)) {
            logInternal(template, values);
        } else {
            logInternal(buildTemplateSplitBySpace(values.length + 1), ArrayUtil.insert(values, 0, template));
        }
    }

    /**
     * 同 System.out.println()方法，打印控制台日志
     *
     * @param t        异常对象
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void log(Throwable t, String template, Object... values) {
        out.println(StrUtil.format(template, values));
        if (null != t) {
            //noinspection CallToPrintStackTrace
            t.printStackTrace();
            out.flush();
        }
    }

    /**
     * 同 System.out.println()方法，打印控制台日志
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void logInternal(String template, Object... values) {
        log(null, template, values);
    }

    // --------------------------------------------------------------------------------- print

    /**
     * 打印表格到控制台
     *
     * @param consoleTable 控制台表格
     */
    static void table(ConsoleTable consoleTable) {
        print(consoleTable.toString());
    }

    /**
     * 同 System.out.print()方法，打印控制台日志
     *
     * @param obj 要打印的对象
     */
    static void print(Object obj) {
        print(TEMPLATE_VAR, obj);
    }

    /**
     * 同 System.out.println()方法，打印控制台日志<br>
     * 如果传入打印对象为{@link Throwable}对象，那么同时打印堆栈
     *
     * @param obj1      第一个要打印的对象
     * @param otherObjs 其它要打印的对象
     */
    static void print(Object obj1, Object... otherObjs) {
        if (ArrayUtil.isEmpty(otherObjs)) {
            print(obj1);
        } else {
            print(buildTemplateSplitBySpace(otherObjs.length + 1), ArrayUtil.insert(otherObjs, 0, obj1));
        }
    }

    /**
     * 同 System.out.print()方法，打印控制台日志
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void print(String template, Object... values) {
        if (ArrayUtil.isEmpty(values) || StrUtil.contains(template, TEMPLATE_VAR)) {
            printInternal(template, values);
        } else {
            printInternal(buildTemplateSplitBySpace(values.length + 1), ArrayUtil.insert(values, 0, template));
        }
    }

    /**
     * 打印进度条
     *
     * @param showChar 进度条提示字符，例如“#”
     * @param len      打印长度
     * @apiNote showcase <pre>{@code
     *     Seq.range(100).forEach(i -> {
     *         Lang.sleep(Duration.ofMillis(100));
     *         Console.printProgress('#', i);
     *     });
     *     }<pre>
     */
    static void printProgress(char showChar, int len) {
        print("{}{}", CharUtil.CR, StrUtil.repeat(showChar, len));
    }

    /**
     * 打印进度条
     *
     * @param showChar 进度条提示字符，例如“#”
     * @param totalLen 总长度
     * @param rate     总长度所占比取值0~1
     */
    static void printProgress(char showChar, int totalLen, double rate) {
        Assert.isTrue(rate >= 0 && rate <= 1, "Rate must between 0 and 1 (both include)");
        printProgress(showChar, (int) (totalLen * rate));
    }

    /**
     * 同 System.out.println()方法，打印控制台日志
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void printInternal(String template, Object... values) {
        out.print(StrUtil.format(template, values));
    }

    // --------------------------------------------------------------------------------- Error

    /**
     * 同 System.err.println()方法，打印控制台日志
     */
    static void error() {
        err.println();
    }

    /**
     * 同 System.err.println()方法，打印控制台日志
     *
     * @param obj 要打印的对象
     */
    static void error(Object obj) {
        if (obj instanceof Throwable) {
            Throwable e = (Throwable) obj;
            error(e, e.getMessage());
        } else {
            error(TEMPLATE_VAR, obj);
        }
    }

    /**
     * 同 System.out.println()方法，打印控制台日志<br>
     * 如果传入打印对象为{@link Throwable}对象，那么同时打印堆栈
     *
     * @param obj1      第一个要打印的对象
     * @param otherObjs 其它要打印的对象
     */
    static void error(Object obj1, Object... otherObjs) {
        if (ArrayUtil.isEmpty(otherObjs)) {
            error(obj1);
        } else {
            error(buildTemplateSplitBySpace(otherObjs.length + 1), ArrayUtil.insert(otherObjs, 0, obj1));
        }
    }

    /**
     * 同 System.err.println()方法，打印控制台日志
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void error(String template, Object... values) {
        if (ArrayUtil.isEmpty(values) || StrUtil.contains(template, TEMPLATE_VAR)) {
            errorInternal(template, values);
        } else {
            errorInternal(buildTemplateSplitBySpace(values.length + 1), ArrayUtil.insert(values, 0, template));
        }
    }

    /**
     * 同 System.err.println()方法，打印控制台日志
     *
     * @param t        异常对象
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void error(Throwable t, String template, Object... values) {
        err.println(StrUtil.format(template, values));
        if (null != t) {
            t.printStackTrace(err);
            err.flush();
        }
    }

    /**
     * 同 System.err.println()方法，打印控制台日志
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param values   值
     */
    static void errorInternal(String template, Object... values) {
        error(null, template, values);
    }

    // --------------------------------------------------------------------------------- console lineNumber

    /**
     * 返回当前位置+行号 (不支持Lambda、内部类、递归内使用)
     *
     * @return 返回当前行号
     * @author dahuoyzs
     */
    static String where() {
        final StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        final String className = stackTraceElement.getClassName();
        final String methodName = stackTraceElement.getMethodName();
        final String fileName = stackTraceElement.getFileName();
        final Integer lineNumber = stackTraceElement.getLineNumber();
        return String.format("%s.%s(%s:%s)", className, methodName, fileName, lineNumber);
    }

    /**
     * 返回当前行号 (不支持Lambda、内部类、递归内使用)
     *
     * @return 返回当前行号
     */
    static Integer lineNumber() {
        return new Throwable().getStackTrace()[1].getLineNumber();
    }

    /**
     * 构建空格分隔的模板，类似于"{} {} {} {}"
     *
     * @param count 变量数量
     * @return 模板
     */
    static String buildTemplateSplitBySpace(int count) {
        return StrUtil.repeatAndJoin(TEMPLATE_VAR, count, StrUtil.SPACE);
    }
}