package io.github.fzdwx.lambada.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * TaskQueue.
 */
public interface TaskQueue<E> extends BlockingQueue<E> {

    /**
     * get executor.
     *
     * @return the executor
     */
    EagerExecutorService getExecutor();

    /**
     * set the executor.
     *
     * @param executor executor
     */
    void setExecutor(EagerExecutorService executor);

    @Override
    default boolean offer(final E e) {
        if (getExecutor() == null) {
            throw new RejectedExecutionException("The task queue does not have executor!");
        }

        int currentPoolThreadSize = getExecutor().getPoolSize();
        // have free worker. put task into queue to let the worker deal with task.
        if (getExecutor().getActiveCount() < currentPoolThreadSize) {
            return doOffer(e);
        }

        // return false to let executor create new worker.
        if (currentPoolThreadSize < getExecutor().getMaximumPoolSize()) {
            return false;
        }

        // currentPoolThreadSize >= max
        return doOffer(e);
    }

    /**
     * offer element to the queue.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else {@code false}
     */
    boolean doOffer(E e);

    /**
     * retry offer task.
     *
     * @param o       task
     * @param timeout timeout
     * @param unit    timeout unit
     * @return offer success or not
     * @throws java.util.concurrent.RejectedExecutionException if executor is terminated.
     * @throws java.lang.InterruptedException                  if the current thread is interrupted.
     */
    default boolean retryOffer(final E o, final long timeout, final TimeUnit unit) throws InterruptedException {
        if (getExecutor().isShutdown()) {
            throw new RejectedExecutionException("Executor is shutdown!");
        }
        return offer(o, timeout, unit);
    }
}