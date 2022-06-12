package io.github.fzdwx.lambada.concurrency;

import java.util.Collection;

/**
 * MemorySafeTaskQueue in the {@link ShenyuThreadPoolExecutor}.
 * It offer a task if the executor's submittedTaskCount less than currentPoolThreadSize
 * or the currentPoolThreadSize more than executor's maximumPoolSize.
 * That can make the executor create new worker
 * when the task num is bigger than corePoolSize but less than maximumPoolSize.
 */
public class MemorySafeTaskQueue<R extends Runnable> extends MemorySafeLinkedBlockingQueue<Runnable> implements TaskQueue<Runnable> {

    private static final long serialVersionUID = -1998413481091670338L;

    private EagerExecutorService executor;

    /**
     * set max free memory is {@link #THE_256_MB}
     */
    public MemorySafeTaskQueue() {
        super();
    }

    public MemorySafeTaskQueue(final int maxFreeMemory) {
        super(maxFreeMemory);
    }

    public MemorySafeTaskQueue(final Collection<? extends Runnable> c, final int maxFreeMemory) {
        super(c, maxFreeMemory);
    }

    @Override
    public EagerExecutorService getExecutor() {
        return this.executor;
    }

    @Override
    public void setExecutor(final EagerExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public boolean doOffer(final Runnable runnable) {
        return super.offer(runnable);
    }

}