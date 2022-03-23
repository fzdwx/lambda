package io.github.fzdwx.lambada;

/**
 * @author <a href="mailto:likelovec@gmail.com">韦朕</a>
 * @date 2022/3/23 12:03
 */
public abstract class Exceptions {

    public static RuntimeException propagate(Throwable t) {
        throwIfFatal(t);
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        }
        return new LambdaException(t);
    }

    public static void throwIfFatal(Throwable t) {
        throwIfJvmFatal(t);
    }

    public static void throwIfJvmFatal(Throwable t) {
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof LinkageError) {
            throw (LinkageError) t;
        }
    }


    static class LambdaException extends RuntimeException {

        LambdaException(final Throwable t) {
            super(t);
        }

        LambdaException(String message) {
            super(message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return getCause() != null ? getCause().fillInStackTrace() :
                    super.fillInStackTrace();
        }
    }
}