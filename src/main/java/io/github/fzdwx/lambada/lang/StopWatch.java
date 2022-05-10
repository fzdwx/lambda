package io.github.fzdwx.lambada.lang;

import io.github.fzdwx.lambada.Exceptions;
import io.github.fzdwx.lambada.Lang;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/3 11:50
 */
@RequiredArgsConstructor
public class StopWatch {

    private final long startTime;
    private final String taskName;

    private static Map<String, StopWatch> cache = new ConcurrentHashMap<>();

    public static StopWatch get(String taskName) {
        if (Lang.isBlank(taskName)) {
            Exceptions.illegalArgument("taskName is blank");
        }

        final StopWatch stopWatch = new StopWatch(System.currentTimeMillis(), taskName);
        cache.put(taskName, stopWatch);
        return stopWatch;
    }

    /**
     * stop the task.
     *
     * @return ms
     */
    public long stop() {
        final long ms = Time.now() - this.startTime;
        cache.remove(this.taskName);
        return ms;
    }

    public void stopAndPrint() {
        System.out.println("taskName: " + this.taskName + " cost: " + (Time.now() - this.startTime) + " ms");
        cache.remove(this.taskName);
    }
}