package io.github.fzdwx.lambada.internal;

import io.github.fzdwx.lambada.Seq;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LongSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/**
 * {@link java.util.stream.LongStream} wrap
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/14 16:45
 */
@NoArgsConstructor
@AllArgsConstructor
public class LongStream implements java.util.stream.LongStream {

    private java.util.stream.LongStream stream;

    @Override
    public LongStream filter(final LongPredicate predicate) {
        this.stream = stream.filter(predicate);
        return this;
    }

    @Override
    public LongStream map(final LongUnaryOperator mapper) {
        this.stream = stream.map(mapper);
        return this;
    }

    @Override
    public <U> Seq<U> mapToObj(final LongFunction<? extends U> mapper) {
        return Seq.of(stream.mapToObj(mapper));
    }

    @Override
    public IntStream mapToInt(final LongToIntFunction mapper) {
        return new IntStream(stream.mapToInt(mapper));
    }

    @Override
    public DoubleStream mapToDouble(final LongToDoubleFunction mapper) {
        return new DoubleStream(stream.mapToDouble(mapper));
    }

    @Override
    public LongStream flatMap(final LongFunction<? extends java.util.stream.LongStream> mapper) {
        this.stream = stream.flatMap(mapper);
        return this;
    }

    @Override
    public LongStream distinct() {
        this.stream = stream.distinct();
        return this;
    }

    @Override
    public LongStream sorted() {
        this.stream = stream.sorted();
        return this;
    }

    @Override
    public LongStream peek(final LongConsumer action) {
        this.stream = stream.peek(action);
        return this;
    }

    @Override
    public LongStream limit(final long maxSize) {
        this.stream = stream.limit(maxSize);
        return this;
    }

    @Override
    public LongStream skip(final long n) {
        this.stream = stream.skip(n);
        return this;
    }

    @Override
    public void forEach(final LongConsumer action) {
        this.stream.forEach(action);
    }

    public void println() {
        this.stream.forEach(System.out::println);
    }

    @Override
    public void forEachOrdered(final LongConsumer action) {
        this.stream.forEachOrdered(action);
    }

    @Override
    public long[] toArray() {
        return this.stream.toArray();
    }

    @Override
    public long reduce(final long identity, final LongBinaryOperator op) {
        return this.stream.reduce(identity, op);
    }

    @Override
    public OptionalLong reduce(final LongBinaryOperator op) {
        return this.stream.reduce(op);
    }

    @Override
    public <R> R collect(final Supplier<R> supplier, final ObjLongConsumer<R> accumulator, final BiConsumer<R, R> combiner) {
        return this.stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public long sum() {
        return this.stream.sum();
    }

    @Override
    public OptionalLong min() {
        return this.stream.min();
    }

    @Override
    public OptionalLong max() {
        return this.stream.max();
    }

    @Override
    public long count() {
        return this.stream.count();
    }

    @Override
    public OptionalDouble average() {
        return this.stream.average();
    }

    @Override
    public LongSummaryStatistics summaryStatistics() {
        return this.stream.summaryStatistics();
    }

    @Override
    public boolean anyMatch(final LongPredicate predicate) {
        return this.stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(final LongPredicate predicate) {
        return this.stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(final LongPredicate predicate) {
        return this.stream.noneMatch(predicate);
    }

    @Override
    public OptionalLong findFirst() {
        return this.stream.findFirst();
    }

    @Override
    public OptionalLong findAny() {
        return this.stream.findAny();
    }

    @Override
    public DoubleStream asDoubleStream() {
        return new DoubleStream(this.stream.asDoubleStream());
    }

    @Override
    public Seq<Long> boxed() {
        return Seq.of(this.stream.boxed());
    }

    @Override
    public LongStream sequential() {
        this.stream = stream.sequential();
        return this;
    }

    @Override
    public LongStream parallel() {
        this.stream = stream.parallel();
        return this;
    }

    @Override
    public LongStream unordered() {
        this.stream = stream.unordered();
        return this;
    }

    @Override
    public LongStream onClose(final Runnable closeHandler) {
        this.stream = stream.onClose(closeHandler);
        return this;
    }

    @Override
    public void close() {
        this.stream.close();
    }

    @Override
    public PrimitiveIterator.OfLong iterator() {
        return this.stream.iterator();
    }

    @Override
    public Spliterator.OfLong spliterator() {
        return this.stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }
}