package io.github.fzdwx.lambada.fun;

/**
 * state of result.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/2 14:11
 */
public interface State<Value> {

    /**
     * this state is success?
     */
    boolean isSuccess();

    /**
     * this state is failure?
     */
    boolean isFailure();

    /**
     * if state is failure, return the cause.
     */
    Throwable cause();

    /**
     * if state is success, return the value.
     */
    Value get();

    /**
     * reset to failure state.
     */
    <NewValue> State<NewValue> setFail(Throwable cause);

    /**
     * reset to success.
     */
    <NewValue> State<NewValue> setSuccess(NewValue value);

    default <NewValue> State<NewValue> setSuccess() {
        return this.setSuccess(null);
    }

    default void throwCause() throws Throwable {
        throw cause();
    }

    static <Value> State<Value> success(final Value value) {
        return new DefaultState<>(value);
    }

    static <Value> State<Value> failure(final Throwable cause) {
        return new DefaultState<>(cause, null);
    }

    /**
     * default state implementation.
     */
    class DefaultState<Value> implements State<Value> {

        private Throwable cause;

        private Value value;

        private boolean succesed;

        private DefaultState(final Throwable cause, final Value value) {
            this.cause = cause;
            this.value = value;
            this.succesed = false;
        }

        private DefaultState(final Value value) {
            this.value = value;
            this.succesed = true;
        }

        public boolean isSuccess() {
            return succesed;
        }

        @Override
        public boolean isFailure() {
            return !succesed;
        }

        public Throwable cause() {
            return cause;
        }

        public Value get() {
            return value;
        }

        public <NewValue> State<NewValue> setFail(final Throwable cause) {
            return State.failure(cause);
        }

        @Override
        public <NewValue> State<NewValue> setSuccess(final NewValue value) {
            return State.success(value);
        }
    }
}