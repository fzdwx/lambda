package io.github.fzdwx.lambada;


import io.github.fzdwx.lambada.anno.NonNull;
import io.github.fzdwx.lambada.internal.IntStream;
import io.github.fzdwx.lambada.internal.SeqImpl;
import io.github.fzdwx.lambada.internal.Tuple2;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * seq.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/2/23 20:39
 * @since 0.01
 */
public interface Seq<T> extends Stream<T> {

    static <T> Seq<T> of(final Stream<T> stream) {
        return new SeqImpl<>(stream);
    }

    @SafeVarargs
    static <T> Seq<T> of(final T... val) {
        return new SeqImpl<>(Stream.of(val));
    }

    static <T> Seq<T> of(final T val) {
        return new SeqImpl<>(Stream.of(val));
    }

    static <T> Seq<T> of(final Collection<T> coll) {
        return new SeqImpl<>(coll.stream());
    }

    static <T> Seq<T> empty() {
        return new SeqImpl<>(Stream.empty());
    }

    static <T> Seq<T> generate(Supplier<T> supplier) {
        return new SeqImpl<>(Stream.generate(supplier));
    }

    static <T> Seq<T> iterate(final T seed, final UnaryOperator<T> f) {
        return new SeqImpl<>(Stream.iterate(seed, f));
    }

    /**
     * 返回从startInclusive （包含）到endExclusive （不包含）的顺序有序IntStream ，增量步长为1
     *
     * @param startInclusive （包含）初始值
     * @param endExclusive   独占上限
     */
    static IntStream range(final int startInclusive, final int endExclusive) {
        return IntStream.range(startInclusive, endExclusive);
    }

    /**
     * 返回从0 （包含）到endExclusive （不包含）的顺序有序IntStream ，增量步长为1
     *
     * @param endExclusive 独占上限
     */
    static IntStream range(final int endExclusive) {
        return IntStream.range(0, endExclusive);
    }

    /**
     * just foreach
     */
    void forEach(final Runnable action);

    /**
     * Returns a limited interval from a given Stream.
     * <p>
     * <code><pre>
     * // (4, 5)
     * Seq.of(1, 2, 3, 4, 5, 6).slice(3, 5)
     * </pre></code>
     *
     * @see #slice(Stream, long, long)
     */
    default Seq<? extends T> slice(final long from, final long to) {
        return slice(this, from, to);
    }

    /**
     * Transform this stream into a new type.
     * <p>
     * If certain operations are re-applied frequently to streams, this
     * transform operation is very useful for such operations to be applied in a
     * fluent style:
     * <p>
     * <code><pre>
     * Function&ltSeq&lt;Integer>, Seq&lt;String>> toString = s -> s.map(Objects::toString);
     * Seq&lt;String> strings =
     * Seq.of(1, 2, 3)
     *    .transform(toString);
     * </pre></code>
     */
    default <U> U transform(final Function<? super Seq<T>, ? extends U> transformer) {
        return transformer.apply(this);
    }

    /**
     * cast to {@code <U>}
     *
     * @return {@link Seq<U> }
     */
    default <U> Seq<U> typeOf(final Class<? extends U> clazz) {
        return this.map(clazz::cast);
    }

    /**
     * Check if the sequence has any elements
     */
    default boolean isEmpty() {
        return this.findFirst().isPresent();
    }

    /**
     * Check if the sequence has no elements
     */
    default boolean isNotEmpty() {
        return !this.isEmpty();
    }

    /**
     * collect to map.
     *
     * @apiNote this method is a shortcut for {@code collect(Collectors.toMap(keyMapper, valueMapper))},
     */
    default <K, V> Map<K, V> toMap(final Function<? super T, ? extends K> keyMapper,
                                   final Function<? super T, ? extends V> valueMapper) {
        return this.collect(Collectors.toMap(keyMapper, valueMapper));
    }

    /**
     * @apiNote the value is {@code <T> }
     * @see #toMap(Seq, Function, Function)
     */
    default <K> Map<K, T> toMap(final Function<? super T, ? extends K> keyMapper) {
        return this.collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    /**
     * 分组
     *
     * @apiNote this method is a shortcut for {@code collect(Collectors.groupingBy(keyMapper))},
     */
    default <K> Map<K, List<T>> groupingBy(final Function<? super T, ? extends K> classifier) {
        return this.collect(Collectors.groupingBy(classifier));
    }

    /**
     * 分组
     *
     * @apiNote this method is a shortcut for {@code collect(Collectors.groupingBy(classifier, Collectors.mapping(valueMapper, Collectors.toList())))},
     */
    default <K, U> Map<K, List<U>> groupingBy(final Function<? super T, ? extends K> classifier,
                                              final Function<? super T, ? extends U> valueMapper) {
        return this.collect(Collectors.groupingBy(classifier, Collectors.mapping(valueMapper, Collectors.toList())));
    }

    /**
     * 收集
     *
     * @param p p
     * @return {@link Tuple2 <List<T>, List<T>> }
     * @apiNote 根据传入的断言收集, 如果断言为true, 则到第一个list中，否则到第二个list中
     */
    default Tuple2<List<T>, List<T>> collect(final Predicate<T> p) {
        final List<T> trueList = new ArrayList<>();
        final List<T> falseList = new ArrayList<>();
        this.forEach(t -> {
            if (p.test(t)) {
                trueList.add(t);
            } else falseList.add(t);
        });

        return Tuple.of(trueList, falseList);
    }

    /**
     * Filter out null data.
     *
     * @return {@link Seq }<{@link T }>
     */
    default Seq<T> nonNull() {
        return filter(Objects::nonNull);
    }

    /**
     * This is an intermediate operation.
     *
     * @param consumer consumer
     * @return {@link Seq }<{@link T }>
     */
    default Seq<T> and(Consumer<T> consumer) {
        return peek(consumer);
    }

    @Override
    Seq<T> filter(final Predicate<? super T> predicate);

    @Override
    <R> Seq<R> map(final Function<? super T, ? extends R> mapper);

    @Override
    <R> Seq<R> flatMap(final Function<? super T, ? extends Stream<? extends R>> mapper);

    @Override
    Seq<T> distinct();

    @Override
    Seq<T> sorted();

    @Override
    Seq<T> sorted(Comparator<? super T> comparator);

    @Override
    Seq<T> peek(Consumer<? super T> action);

    @Override
    Seq<T> limit(final long maxSize);

    @Override
    Seq<T> skip(final long n);

    /**
     * to list
     *
     * @apiNote this method is a shortcut for {@code collect(Collectors.toList())},
     * Deprecated in JDK16 and above : {@link #toList()}
     * todo 后续专门构建JDK的高版本的API
     */
    List<T> toList2();

    /**
     * to set
     *
     * @apiNote this method is a shortcut for {@code collect(Collectors.toSet())},
     */
    Set<T> toSet2();

    @Override
    Seq<T> sequential();

    @Override
    Seq<T> parallel();

    @Override
    Seq<T> unordered();

    @Override
    Seq<T> onClose(final Runnable closeHandler);

    Seq<T> concat(Stream<? extends T> other);

    Seq<T> concat(T other);

    Seq<T> concat(T... other);

    default void println() {
        this.forEach(v -> System.out.println(v));
    }

    /**
     * @apiNote the value is {@code <T> }
     * @see #toMap(Seq, Function, Function)
     */
    static <K, T> Map<K, T> toMap(@NonNull final Collection<T> collection,
                                  final Function<T, K> keyMapper
    ) {
        if (Lang.isEmpty(collection)) {
            return Collections.map();
        }
        return toMap(Seq.of(collection), keyMapper, Function.identity());
    }

    /**
     * @apiNote the value is {@code <T> }
     * @see #toMap(Seq, Function, Function)
     */
    static <K, T, V> Map<K, V> toMap(@NonNull final Collection<T> collection,
                                     final Function<? super T, ? extends K> keyMapper,
                                     final Function<? super T, ? extends V> valueMapper
    ) {
        if (collection == null) {
            return Collections.map();
        }
        return toMap(Seq.of(collection), keyMapper, valueMapper);
    }

    /**
     * @apiNote the value is {@code <T> }
     * @see #toMap(Seq, Function, Function)
     */
    static <K, T> Map<K, T> toMap(@NonNull final Stream<T> stream,
                                  final Function<T, K> keyMapper
    ) {
        if (stream == null) {
            return Collections.map();
        }
        return toMap(Seq.of(stream), keyMapper, Function.identity());
    }

    /**
     * @apiNote the value is {@code <T> }
     * @see #toMap(Seq, Function, Function)
     */
    static <K, T, V> Map<K, V> toMap(@NonNull final Stream<T> stream,
                                     final Function<? super T, ? extends K> keyMapper,
                                     final Function<? super T, ? extends V> valueMapper
    ) {
        if (stream == null) {
            return Collections.map();
        }
        return toMap(Seq.of(stream), keyMapper, valueMapper);
    }

    /**
     * @apiNote the value is {@code <T> }
     * @see #toMap(Seq, Function, Function)
     */
    static <K, T> Map<K, T> toMap(@NonNull final Seq<T> seq,
                                  final Function<T, K> keyMapper
    ) {
        return seq.toMap(keyMapper);
    }

    /**
     * collect to map.
     *
     * @apiNote this method is a shortcut for {@code collect(Collectors.toMap(keyMapper, valueMapper))},
     */
    static <K, T, V> Map<K, V> toMap(@NonNull final Seq<T> seq,
                                     final Function<? super T, ? extends K> keyMapper,
                                     final Function<? super T, ? extends V> valueMapper
    ) {
        if (seq == null) {
            return Collections.map();
        }
        return seq.toMap(keyMapper, valueMapper);
    }

    /**
     * map.
     *
     * @apiNote this method is a shortcut for {@code stream.map(mapper)},
     */
    static <R, T> Seq<R> map(Collection<T> collection, final Function<? super T, ? extends R> mapper) {
        return Seq.of(collection.stream().map(mapper));
    }

    /**
     * Returns a limited interval from a given Stream.
     * <p>
     * <code><pre>
     * // (4, 5)
     * Seq.of(1, 2, 3, 4, 5, 6).slice(3, 5)
     * </pre></code>
     */
    static <T> Seq<? extends T> slice(final Stream<? extends T> stream, final long from, final long to) {
        final long f = Math.max(from, 0);
        final long t = Math.max(to - f, 0);

        return of(stream.skip(f).limit(t));
    }

    /**
     * Returns a stream with n elements skipped.
     * <p>
     * <code><pre>
     * // (4, 5, 6)
     * Seq.of(1, 2, 3, 4, 5, 6).skip(3)
     * </pre></code>
     */
    static <T> Seq<? extends T> skip(final Stream<? extends T> stream, final long elements) {
        return of(stream.skip(elements));
    }

    /**
     * @see #sort(Collection, Comparator)
     */
    static <T> List<T> sort(@NonNull Stream<T> s) {
        return s.sorted().collect(Collectors.toList());
    }

    /**
     * @see #sort(Collection, Comparator)
     */
    static <T> List<T> sort(@NonNull Stream<T> s, Comparator<? super T> comparator) {
        return s.sorted(comparator).collect(Collectors.toList());
    }

    /**
     * @see #sort(Collection, Comparator)
     */
    static <T> List<T> sort(Collection<T> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    /**
     * @return {@link List }<{@link T }>
     * @apiNote this method is a shortcut for {@code stream().sorted(comparator).collect(Collectors.toList())},
     */
    static <T> List<T> sort(Collection<T> list, Comparator<? super T> comparator) {
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }
}