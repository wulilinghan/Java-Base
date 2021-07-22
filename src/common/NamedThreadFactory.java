package common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ManJiis
 * @since 2021-07-21
 * @since JDK1.8
 */
public class NamedThreadFactory implements ThreadFactory {

    /**
     * 命名前缀
     */
    private final String prefix;
    /**
     * 线程组
     */
    private final ThreadGroup group;
    /**
     * 线程组
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 是否守护线程
     */
    private final boolean isDaemon;
    /**
     * 无法捕获的异常统一处理
     */
    private final Thread.UncaughtExceptionHandler handler;

    /**
     * 构造
     *
     * @param prefix   线程名前缀
     * @param isDaemon 是否守护线程
     */
    public NamedThreadFactory(String prefix, boolean isDaemon) {
        this(prefix, null, isDaemon);
    }

    /**
     * 构造
     *
     * @param prefix      线程名前缀
     * @param threadGroup 线程组，可以为null
     * @param isDaemon    是否守护线程
     */
    public NamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDaemon) {
        this(prefix, threadGroup, isDaemon, null);
    }

    /**
     * 构造
     *
     * @param prefix      线程名前缀
     * @param threadGroup 线程组，可以为null
     * @param isDaemon    是否守护线程
     * @param handler     未捕获异常处理
     */
    public NamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDaemon, Thread.UncaughtExceptionHandler handler) {
        this.prefix = prefix;
        if (null == threadGroup) {
            threadGroup = currentThreadGroup();
        }
        this.group = threadGroup;
        this.isDaemon = isDaemon;
        this.handler = handler;
    }

    public static ThreadGroup currentThreadGroup() {
        final SecurityManager s = System.getSecurityManager();
        return (null != s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        final Thread t = new Thread(this.group, r, String.format("%s%s", prefix, threadNumber.getAndIncrement()));

        //守护线程
        if (!t.isDaemon()) {
            if (isDaemon) {
                // 原线程为非守护则设置为守护
                t.setDaemon(true);
            }
        } else if (!isDaemon) {
            // 原线程为守护则还原为非守护
            t.setDaemon(false);
        }
        //异常处理
        if (null != this.handler) {
            t.setUncaughtExceptionHandler(handler);
        }
        //优先级
        if (Thread.NORM_PRIORITY != t.getPriority()) {
            // 标准优先级
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    public static boolean isBlank(CharSequence str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a';
    }
}
