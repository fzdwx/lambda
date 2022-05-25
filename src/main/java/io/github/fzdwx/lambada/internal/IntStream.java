package io.github.fzdwx.lambada.internal;

import io.github.fzdwx.lambada.Seq;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/**
 * {@link java.util.stream.IntStream} wrap.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/14 16:39
 */
@NoArgsConstructor
@AllArgsConstructor
public class IntStream implements java.util.stream.IntStream {

    private java.util.stream.IntStream stream;

    public static IntStream range(int startInclusive, int endExclusive) {
        return new IntStream(java.util.stream.IntStream.range(startInclusive, endExclusive));
    }

    @Override
    public IntStream filter(final IntPredicate predicate) {
        this.stream = stream.filter(predicate);
        return this;
    }

    @Override
    public IntStream map(final IntUnaryOperator mapper) {
        this.stream = stream.map(mapper);
        return this;
    }

    @Override
    public <U> Seq<U> mapToObj(final IntFunction<? extends U> mapper) {
        return Seq.of(stream.mapToObj(mapper));
    }

    @Override
    public LongStream mapToLong(final IntToLongFunction mapper) {
        return new LongStream(stream.mapToLong(mapper));
    }

    @Override
    public DoubleStream mapToDouble(final IntToDoubleFunction mapper) {
        return new DoubleStream(stream.mapToDouble(mapper));
    }

    @Override
    public IntStream flatMap(final IntFunction<? extends java.util.stream.IntStream> mapper) {
        this.stream = stream.flatMap(mapper);
        return this;
    }

    @Override
    public IntStream distinct() {
        this.stream = stream.distinct();
        return this;
    }

    @Override
    public IntStream sorted() {
        this.stream = stream.sorted();
        return this;
    }

    @Override
    public IntStream peek(final IntConsumer action) {
        this.stream = stream.peek(action);
        return this;
    }

    @Override
    public IntStream limit(final long maxSize) {
        this.stream = stream.limit(maxSize);
        return this;
    }

    @Override
    public IntStream skip(final long n) {
        this.stream = stream.skip(n);
        return this;
    }

    @Override
    public void forEach(final IntConsumer action) {
        this.stream.forEach(action);
    }

    @Override
    public void forEachOrdered(final IntConsumer action) {
        this.stream.forEachOrdered(action);
    }

    @Override
    public int[] toArray() {
        return this.stream.toArray();
    }

    @Override
    public int reduce(final int identity, final IntBinaryOperator op) {
        return this.stream.reduce(identity, op);
    }

    @Override
    public OptionalInt reduce(final IntBinaryOperator op) {
        return this.stream.reduce(op);
    }

    @Override
    public <R> R collect(final Supplier<R> supplier, final ObjIntConsumer<R> accumulator, final BiConsumer<R, R> combiner) {
        return this.stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public int sum() {
        return this.stream.sum();
    }

    @Override
    public OptionalInt min() {
        return this.stream.min();
    }

    @Override
    public OptionalInt max() {
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
    public IntSummaryStatistics summaryStatistics() {
        return this.stream.summaryStatistics();
    }

    @Override
    public boolean anyMatch(final IntPredicate predicate) {
        return this.stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(final IntPredicate predicate) {
        return this.stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(final IntPredicate predicate) {
        return this.stream.noneMatch(predicate);
    }

    @Override
    public OptionalInt findFirst() {
        return this.stream.findFirst();
    }

    @Override
    public OptionalInt findAny() {
        return this.stream.findAny();
    }

    @Override
    public LongStream asLongStream() {
        return new LongStream(this.stream.asLongStream());
    }

    @Override
    public DoubleStream asDoubleStream() {
        return new DoubleStream(this.stream.asDoubleStream());
    }

    @Override
    public Seq<Integer> boxed() {
        return Seq.of(this.stream.boxed());
    }

    @Override
    public IntStream sequential() {
        this.stream = stream.sequential();
        return this;
    }

    @Override
    public IntStream parallel() {
        this.stream = stream.parallel();
        return this;
    }

    @Override
    public PrimitiveIterator.OfInt iterator() {
        return this.stream.iterator();
    }

    @Override
    public Spliterator.OfInt spliterator() {
        return this.stream.spliterator();
    }

    public void println() {
        this.stream.forEach(System.out::println);
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }

    @Override
    public IntStream unordered() {
        this.stream = stream.unordered();
        return this;
    }

    @Override
    public IntStream onClose(final Runnable closeHandler) {
        this.stream = stream.onClose(closeHandler);
        return this;
    }

    @Override
    public void close() {
        this.stream.close();
    }
}