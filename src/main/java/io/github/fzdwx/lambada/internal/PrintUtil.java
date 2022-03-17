package io.github.fzdwx.lambada.internal;

/**
 * 打印跑龙套
 *
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/03/17 11:46:31
 */
public class PrintUtil {

    public static void printBanner() {
        System.out.println("    ____          __             ");
        System.out.println("   / __/___  ____/ /      __" + PrintUtil.ANSI_CYAN + "_  __" + PrintUtil.ANSI_RESET);
        System.out.println("  / /_/_  / /" + PrintUtil.ANSI_RED + " __" + PrintUtil.ANSI_RESET + "  / | /| / " + PrintUtil.ANSI_CYAN + "/ |/_/" + PrintUtil.ANSI_RESET);
        System.out.println(" / __/ / /_/ " + PrintUtil.ANSI_RED + "/_/" + PrintUtil.ANSI_RESET + " /| |/ |/ /" + PrintUtil.ANSI_CYAN + ">  <  " + PrintUtil.ANSI_RESET);
        System.out.println("/_/   /___/\\__,_/ |__/|__" + PrintUtil.ANSI_CYAN + "/_/|_|  " + PrintUtil.ANSI_RESET + " ::https://github.com/fzdwx:: ");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void blackln(Object txt) {
        System.out.println(ANSI_BLACK + txt);
        System.out.print(ANSI_RESET);
    }

    public static void redln(Object txt) {
        System.out.println(ANSI_RED + txt);
        System.out.print(ANSI_RESET);
    }

    public static void blueln(Object txt) {
        System.out.println(ANSI_BLUE + txt);
        System.out.print(ANSI_RESET);
    }

    public static void greenln(Object txt) {
        System.out.println(ANSI_GREEN + txt);
        System.out.print(ANSI_RESET);
    }

    public static void purpleln(String txt) {
        System.out.println(ANSI_PURPLE + txt);
        System.out.print(ANSI_RESET);
    }

    public static void yellowln(Object txt) {
        System.out.println(ANSI_YELLOW + txt);
        System.out.print(ANSI_RESET);
    }

    public static void debug(Object content) {
        System.out.print("[fzdwx] ");
        blueln(content);
    }

    public static void debug(String label, Object content) {
        System.out.print("[fzdwx] ");
        blueln(label + ": " + content);
    }

    public static void info(Object content) {
        System.out.println("[fzdwx] " + content);
    }

    public static void info(String label, Object content) {
        System.out.print("[fzdwx] ");
        greenln(label + ": " + content);
    }

    public static void warn(Object content) {
        System.out.print("[fzdwx] ");
        yellowln(content);
    }

    public static void warn(String label, Object content) {
        System.out.print("[fzdwx] ");
        yellowln(label + ": " + content);
    }

    public static void error(Object content) {
        System.out.print("[fzdwx] ");
        redln(content);
    }

    public static void error(String label, Object content) {
        System.out.print("[fzdwx] ");
        redln(label + ": " + content);
    }
}