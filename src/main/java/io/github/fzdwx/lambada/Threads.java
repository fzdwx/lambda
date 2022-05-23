package io.github.fzdwx.lambada;

import java.time.Duration;

/**
 * threads.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/17 14:36
 */
public interface Threads {

    /**
     * return current thread.
     */
    static Thread current() {
        return Thread.currentThread();
    }

    /**
     * sleep current thread.
     *
     * @param millis 睡眠时间
     * @return on interrupt return false, else return true
     * @see Thread#sleep(long)
     */
    static boolean sleep(long millis) {
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (final InterruptedException ignore) {
                return false;
            }
        }
        return true;
    }

    /**
     * @see #sleep(long)
     */
    static boolean sleep(final Duration sleepTime) {
        if (sleepTime == null) return false;
        return sleep(sleepTime.toMillis());
    }
}