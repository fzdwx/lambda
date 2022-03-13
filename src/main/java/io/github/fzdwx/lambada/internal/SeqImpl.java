package io.github.fzdwx.lambada.internal;

import io.github.fzdwx.lambada.Seq;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/2/23 20:51
 */
public class SeqImpl<T> implements Seq<T> {

    private Stream<T> stream;

    public SeqImpl(final Stream<T> source) {
        this.stream = source;
    }

    @Override
    public Seq<T> filter(final Predicate<? super T> predicate) {
        this.stream = this.stream.filter(predicate);
        return this;
    }

    @Override
    public <R> Seq<R> map(final Function<? super T, ? extends R> mapper) {
        return new SeqImpl<>(this.stream.map(mapper));
    }

    @Override
    public IntStream mapToInt(final ToIntFunction<? super T> mapper) {
        return this.stream.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(final ToLongFunction<? super T> mapper) {
        return this.stream.mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(final ToDoubleFunction<? super T> mapper) {
        return this.stream.mapToDouble(mapper);
    }

    @Override
    public <R> Seq<R> flatMap(final Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new SeqImpl<>(this.stream.flatMap(mapper));
    }

    @Override
    public IntStream flatMapToInt(final Function<? super T, ? extends IntStream> mapper) {
        return this.stream.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(final Function<? super T, ? extends LongStream> mapper) {
        return this.stream.flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(final Function<? super T, ? extends DoubleStream> mapper) {
        return this.stream.flatMapToDouble(mapper);
    }

    @Override
    public Seq<T> distinct() {
        this.stream = this.stream.distinct();
        return this;
    }

    @Override
    public Seq<T> sorted() {
        this.stream = this.stream.sorted();
        return this;
    }

    @Override
    public Seq<T> sorted(final Comparator<? super T> comparator) {
        this.stream = this.stream.sorted(comparator);
        return this;
    }

    @Override
    public Seq<T> peek(final Consumer<? super T> action) {
        this.stream = this.stream.peek(action);
        return this;
    }

    @Override
    public Seq<T> limit(final long maxSize) {
        this.stream = this.stream.limit(maxSize);
        return this;
    }

    @Override
    public Seq<T> skip(final long n) {
        this.stream = this.stream.skip(n);
        return this;
    }

    @Override
    public void forEach(final Consumer<? super T> action) {
        this.stream.forEach(action);
    }

    @Override
    public void forEachOrdered(final Consumer<? super T> action) {
        this.stream.forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return this.stream.toArray();
    }

    @Override
    public <A> A[] toArray(final IntFunction<A[]> generator) {
        return this.stream.toArray(generator);
    }

    @Override
    public T reduce(final T identity, final BinaryOperator<T> accumulator) {
        return this.stream.reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(final BinaryOperator<T> accumulator) {
        return this.stream.reduce(accumulator);
    }

    @Override
    public <U> U reduce(final U identity, final BiFunction<U, ? super T, U> accumulator, final BinaryOperator<U> combiner) {
        return this.stream.reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(final Supplier<R> supplier, final BiConsumer<R, ? super T> accumulator, final BiConsumer<R, R> combiner) {
        return this.stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(final Collector<? super T, A, R> collector) {
        return this.stream.collect(collector);
    }

    @Override
    public Optional<T> min(final Comparator<? super T> comparator) {
        return this.stream.min(comparator);
    }

    @Override
    public Optional<T> max(final Comparator<? super T> comparator) {
        return this.stream.min(comparator);
    }

    @Override
    public long count() {
        return this.stream.count();
    }

    @Override
    public boolean anyMatch(final Predicate<? super T> predicate) {
        return this.stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(final Predicate<? super T> predicate) {
        return this.stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(final Predicate<? super T> predicate) {
        return this.stream.noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return this.stream.findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return this.stream.findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return this.stream.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return this.stream.isParallel();
    }

    @Override
    public Seq<T> sequential() {
        this.stream = this.stream.sequential();
        return this;
    }

    @Override
    public Seq<T> parallel() {
        this.stream = this.stream.parallel();
        return this;
    }

    @Override
    public Seq<T> unordered() {
        this.stream = this.stream.unordered();
        return this;
    }

    @Override
    public Seq<T> onClose(final Runnable closeHandler) {
        this.stream = this.stream.onClose(closeHandler);
        return this;
    }

    @Override
    public void close() {
        this.stream.close();
    }

    @Override
    public Seq<T> concat(final Stream<? extends T> other) {
        this.stream = Stream.concat(this.stream, other);
        return this;
    }

    @Override
    public Set<T> toSet() {
        return this.stream.collect(Collectors.toSet());
    }

    @Override
    public List<T> toList() {
        return this.stream.collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stream);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final SeqImpl<?> seq = (SeqImpl<?>) o;
        return Objects.equals(this.stream, seq.stream);
    }

    @Override
    public String toString() {
        return this.stream.toString();
    }
}