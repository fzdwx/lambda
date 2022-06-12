package io.github.fzdwx.lambada.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/6/8 10:40
 */
class MemorySafeLinkedBlockingQueueTest {

    @Test
    public void test() throws Exception {
        MemorySafeLinkedBlockingQueue<Runnable> queue = new MemorySafeLinkedBlockingQueue<>(Integer.MAX_VALUE);
        // all memory is reserved for JVM, so it will fail here
        assertFalse(queue.offer(() -> { }));

        // only 1 Byte memory is reserved for the JVM, so this will succeed
        queue.setMaxFreeMemory(1);
        assertTrue(queue.offer(() -> { }));
    }

    @Test
    void name() {
        final MemorySafeTaskQueue<Runnable> memorySafeTaskQueue = new MemorySafeTaskQueue<>(1);
        System.out.println(memorySafeTaskQueue.getExecutor());
    }
}