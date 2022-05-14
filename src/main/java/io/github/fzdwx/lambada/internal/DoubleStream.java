package io.github.fzdwx.lambada.internal;

import io.github.fzdwx.lambada.Seq;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;

/**
 * {@link java.util.stream.DoubleStream} wrap
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/14 16:46
 */
@NoArgsConstructor
@AllArgsConstructor
public class DoubleStream implements java.util.stream.DoubleStream {

    private java.util.stream.DoubleStream stream;

    @Override
    public DoubleStream filter(final DoublePredicate predicate) {
        this.stream = stream.filter(predicate);
        return this;
    }

    @Override
    public DoubleStream map(final DoubleUnaryOperator mapper) {
        this.stream = stream.map(mapper);
        return this;
    }

    @Override
    public <U> Seq<U> mapToObj(final DoubleFunction<? extends U> mapper) {
        return Seq.of(stream.mapToObj(mapper));
    }

    @Override
    public IntStream mapToInt(final DoubleToIntFunction mapper) {
        return new IntStream(stream.mapToInt(mapper));
    }

    @Override
    public LongStream mapToLong(final DoubleToLongFunction mapper) {
        return new LongStream(stream.mapToLong(mapper));
    }


    @Override
    public DoubleStream flatMap(final DoubleFunction<? extends java.util.stream.DoubleStream> mapper) {
        this.stream = stream.flatMap(mapper);
        return this;
    }

    @Override
    public DoubleStream distinct() {
        this.stream = stream.distinct();
        return this;
    }

    @Override
    public DoubleStream sorted() {
        this.stream = stream.sorted();
        return this;
    }

    @Override
    public DoubleStream peek(final DoubleConsumer action) {
        this.stream = stream.peek(action);
        return this;
    }

    @Override
    public DoubleStream limit(final long maxSize) {
        this.stream = stream.limit(maxSize);
        return this;
    }

    @Override
    public DoubleStream skip(final long n) {
        this.stream = stream.skip(n);
        return this;
    }

    @Override
    public void forEach(final DoubleConsumer action) {
        this.stream.forEach(action);
    }

    public void println() {
        this.stream.forEach(System.out::println);
    }

    @Override
    public void forEachOrdered(final DoubleConsumer action) {
        this.stream.forEachOrdered(action);
    }

    @Override
    public double[] toArray() {
        return this.stream.toArray();
    }

    @Override
    public double reduce(final double identity, final DoubleBinaryOperator op) {
        return this.stream.reduce(identity, op);
    }

    @Override
    public OptionalDouble reduce(final DoubleBinaryOperator op) {
        return this.stream.reduce(op);
    }

    @Override
    public <R> R collect(final Supplier<R> supplier, final ObjDoubleConsumer<R> accumulator, final BiConsumer<R, R> combiner) {
        return this.stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public double sum() {
        return this.stream.sum();
    }

    @Override
    public OptionalDouble min() {
        return this.stream.min();
    }

    @Override
    public OptionalDouble max() {
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
    public DoubleSummaryStatistics summaryStatistics() {
        return this.stream.summaryStatistics();
    }

    @Override
    public boolean anyMatch(final DoublePredicate predicate) {
        return this.stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(final DoublePredicate predicate) {
        return this.stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(final DoublePredicate predicate) {
        return this.stream.noneMatch(predicate);
    }

    @Override
    public OptionalDouble findFirst() {
        return this.stream.findFirst();
    }

    @Override
    public OptionalDouble findAny() {
        return this.stream.findAny();
    }

    @Override
    public Seq<Double> boxed() {
        return Seq.of(this.stream.boxed());
    }

    @Override
    public DoubleStream sequential() {
        this.stream = stream.sequential();
        return this;
    }

    @Override
    public DoubleStream parallel() {
        this.stream = stream.parallel();
        return this;
    }

    @Override
    public DoubleStream unordered() {
        this.stream = stream.unordered();
        return this;
    }

    @Override
    public DoubleStream onClose(final Runnable closeHandler) {
        this.stream = stream.onClose(closeHandler);
        return this;
    }

    @Override
    public void close() {
        this.stream.close();
    }

    @Override
    public PrimitiveIterator.OfDouble iterator() {
        return this.stream.iterator();
    }

    @Override
    public Spliterator.OfDouble spliterator() {
        return this.stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }
}