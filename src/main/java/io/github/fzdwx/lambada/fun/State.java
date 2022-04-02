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
    State<Value> setFail(Throwable cause);

    /**
     * new fail and use old cause.
     */
    default <NewValue> State<NewValue> newFail() {
        return State.failure(this.cause());
    }

    /**
     * new fail and use new cause.
     */
    default <NewValue> State<NewValue> newFail(Throwable cause) {
        return State.failure(cause);
    }

    /**
     * new success and use old value.
     */
    default <NewValue> State<NewValue> newSuccess() {
        return State.success(null);
    }

    /**
     * new success and use new value.
     */
    default <NewValue> State<NewValue> newSuccess(NewValue newValue) {
        return State.success(newValue);
    }

    /**
     * reset to success.
     */
    State<Value> setSuccess(Value value);

    /**
     * throw cause.
     *
     * @apiNote no check isFailure.
     */
    default void throwCause() throws Throwable {
        final Throwable cause = cause();

        if (cause == null) {
            throw new IllegalStateException("cause is null");

        }
        throw cause;
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

        @Override
        public State<Value> setFail(final Throwable cause) {
            this.cause = cause;
            this.succesed = false;
            return this;
        }

        @Override
        public State<Value> setSuccess(final Value value) {
            this.value = value;
            this.succesed = true;
            this.cause = null;
            return this;
        }
    }
}