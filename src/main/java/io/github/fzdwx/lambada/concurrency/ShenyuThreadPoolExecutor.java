package io.github.fzdwx.lambada.concurrency;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ShenyuThreadPoolExecutor
 * <pre>
 *     {@code
 * @Bean
 * @ConditionalOnMissingBean(TaskQueue.class)
 * @ConditionalOnProperty("shenyu.sharedPool.maxFreeMemory")
 * public TaskQueue<Runnable> memorySafeTaskQueue(final ShenyuConfig shenyuConfig) {
 *     final ShenyuConfig.SharedPool sharedPool = shenyuConfig.getSharedPool();
 *     final Integer maxFreeMemory = sharedPool.getMaxFreeMemory();
 *     if (maxFreeMemory <= 0) {
 *         throw new ShenyuException("${shenyu.sharedPool.maxFreeMemory} must bigger than 0 !");
 *     }
 *     return new MemorySafeTaskQueue<>(maxFreeMemory);
 * }
 *
 * @Bean
 * @ConditionalOnProperty(name = "shenyu.sharedPool.enable", havingValue = "true")
 * public ShenyuThreadPoolExecutor shenyuThreadPoolExecutor(final ShenyuConfig shenyuConfig,
 *                                                          final ObjectProvider<TaskQueue<Runnable>> provider) {
 *     final ShenyuConfig.SharedPool sharedPool = shenyuConfig.getSharedPool();
 *     final Integer corePoolSize = sharedPool.getCorePoolSize();
 *     final Integer maximumPoolSize = sharedPool.getMaximumPoolSize();
 *     final Long keepAliveTime = sharedPool.getKeepAliveTime();
 *     return new ShenyuThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
 *             TimeUnit.MILLISECONDS, provider.getIfAvailable(() -> new MemorySafeTaskQueue<>(Constants.THE_256_MB)),
 *             ShenyuThreadFactory.create(sharedPool.getPrefix(), true),
 *             new ThreadPoolExecutor.AbortPolicy());
 * }
 *     }
 * </pre>
 */
public class ShenyuThreadPoolExecutor extends ThreadPoolExecutor implements EagerExecutorService {

    public ShenyuThreadPoolExecutor(final int corePoolSize,
                                    final int maximumPoolSize,
                                    final long keepAliveTime,
                                    final TimeUnit unit,
                                    final TaskQueue<Runnable> workQueue,
                                    final ThreadFactory threadFactory,
                                    final RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        workQueue.setExecutor(this);
    }

    @Override
    public void execute(final Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        try {
            super.execute(command);
        } catch (RejectedExecutionException e) {
            // retry to offer the task into queue.
            final TaskQueue<Runnable> queue = (TaskQueue<Runnable>) super.getQueue();
            try {
                if (!queue.retryOffer(command, 0, TimeUnit.MILLISECONDS)) {
                    throw new RejectedExecutionException("Queue capacity is full.", e);
                }
            } catch (InterruptedException t) {
                throw new RejectedExecutionException(t);
            }
        }
    }
}