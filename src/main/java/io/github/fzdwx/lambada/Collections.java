package io.github.fzdwx.lambada;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Sets;
import io.github.fzdwx.lambada.anno.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * coll.
 * <p>
 * a Collection funciton set.
 *
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/2/25 13:10
 * @since 0.01
 */
public interface Collections {

    static <E> List<E> list() {
        return new ArrayList<E>();
    }

    static <E> List<E> list(final Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    static <E> List<E> list(final E element) {
        final List<E> list = new ArrayList<>(1);
        list.add(element);
        return list;
    }

    static <E> List<E> list(final E e1, final E e2) {
        final List<E> list = new ArrayList<>(2);
        list.add(e1);
        list.add(e2);
        return list;
    }

    static <E> List<E> list(final E e1, final E e2, final E e3) {
        final List<E> list = new ArrayList<>(3);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        return list;
    }

    static <E> List<E> list(final E e1, final E e2, final E e3, final E e4) {
        final List<E> list = new ArrayList<>(4);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        return list;
    }

    static <E> List<E> list(final E e1, final E e2, final E e3, final E e4, final E e5) {
        final List<E> list = new ArrayList<>(5);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        return list;
    }

    static <E> List<E> list(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6) {
        final List<E> list = new ArrayList<>(6);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        return list;
    }


    static <E> List<E> list(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7) {
        final List<E> list = new ArrayList<>(7);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        return list;
    }

    static <E> List<E> list(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7, final E e8) {
        final List<E> list = new ArrayList<>(8);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        list.add(e8);
        return list;
    }

    @SafeVarargs
    static <E> List<E> list(final E... elements) {
        final List<E> list = list();
        list.addAll(Arrays.asList(elements));
        return list;
    }

    static <E> List<E> list(Consumer<List<E>> init) {
        final List<E> list = new ArrayList<>();
        init.accept(list);
        return list;
    }

    static <E> LinkedList<E> linkedList() {
        return new LinkedList<>();
    }

    static <E> LinkedList<E> linkedList(final E element) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(element);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2, final E e3) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2, final E e3, final E e4) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2, final E e3, final E e4, final E e5) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        return list;
    }

    static <E> LinkedList<E> linkedList(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7, final E e8) {
        final LinkedList<E> list = new LinkedList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        list.add(e8);
        return list;
    }

    @SafeVarargs
    static <E> LinkedList<E> linkedList(final E... element) {
        return new LinkedList<>(Arrays.asList(element));
    }

    static <E> LinkedList<E> linkedList(Consumer<LinkedList<E>> init) {
        final LinkedList<E> list = new LinkedList<>();
        init.accept(list);
        return list;
    }

    static <E> Set<E> set() {
        return new HashSet<>();
    }

    static <E> Set<E> set(final Stream<E> stream) {
        return stream.collect(Collectors.toSet());
    }

    static <E> Set<E> set(final E element) {
        final Set<E> list = new HashSet<>(1);
        list.add(element);
        return list;
    }

    static <E> Set<E> set(final E e1, final E e2) {
        final Set<E> list = new HashSet<>(2);
        list.add(e1);
        list.add(e2);
        return list;
    }

    static <E> Set<E> set(final E e1, final E e2, final E e3) {
        final Set<E> list = new HashSet<>(3);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        return list;
    }

    static <E> Set<E> set(final E e1, final E e2, final E e3, final E e4) {
        final Set<E> list = new HashSet<>(4);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        return list;
    }

    static <E> Set<E> set(final E e1, final E e2, final E e3, final E e4, final E e5) {
        final Set<E> list = new HashSet<>(5);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        return list;
    }

    static <E> Set<E> set(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6) {
        final Set<E> list = new HashSet<>(6);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        return list;
    }


    static <E> Set<E> set(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7) {
        final Set<E> list = new HashSet<>(7);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        return list;
    }

    static <E> Set<E> set(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7, final E e8) {
        final Set<E> list = new HashSet<>(8);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        list.add(e8);
        return list;
    }

    @SafeVarargs
    static <E> Set<E> set(final E... elements) {
        return Sets.newHashSet(elements);
    }

    static <E> Set<E> set(Consumer<Set<E>> init) {
        final Set<E> list = new HashSet<>();
        init.accept(list);
        return list;
    }

    static <E> Set<E> linkedSet() {
        return new LinkedHashSet<>();
    }

    static <E> Set<E> linkedSet(final E element) {
        final Set<E> list = new LinkedHashSet<>(1);
        list.add(element);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2) {
        final Set<E> list = new LinkedHashSet<>(2);
        list.add(e1);
        list.add(e2);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2, final E e3) {
        final Set<E> list = new LinkedHashSet<>(3);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2, final E e3, final E e4) {
        final Set<E> list = new LinkedHashSet<>(4);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2, final E e3, final E e4, final E e5) {
        final Set<E> list = new LinkedHashSet<>(5);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6) {
        final Set<E> list = new LinkedHashSet<>(6);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7) {
        final Set<E> list = new LinkedHashSet<>(7);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        return list;
    }

    static <E> Set<E> linkedSet(final E e1, final E e2, final E e3, final E e4, final E e5, final E e6, final E e7, final E e8) {
        final Set<E> list = new LinkedHashSet<>(8);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        list.add(e8);
        return list;
    }

    @SafeVarargs
    static <E> Set<E> linkedSet(final E... elements) {
        final LinkedHashSet<E> set = new LinkedHashSet<>(elements.length);
        final boolean b = java.util.Collections.addAll(set, elements);
        return set;
    }

    static <E> Set<E> linkedSet(Consumer<LinkedHashSet<E>> init) {
        final LinkedHashSet<E> list = new LinkedHashSet<>();
        init.accept(list);
        return list;
    }

    static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    static <E, K, V> Map<K, V> map(final Stream<E> stream,
                                   final Function<? super E, ? extends K> keyMapper,
                                   final Function<? super E, ? extends V> valueMapper) {
        return stream.collect(Collectors.toMap(keyMapper, valueMapper));
    }

    static <K, V> Map<K, V> map(@NonNull final List<K> keys, @NonNull final List<V> values) {
        if (Lang.eq(keys.size(), values.size())) {
            throw new IllegalArgumentException(Lang.format("Func map error : keys size :{},values size:{}; please check ", keys.size(), values.size()));
        }
        final Map<K, V> map = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }


    static <K, V> Map<K, V> map(final K key, final V val) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(key, val);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                final V v5) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                final V v5, final K k6, final V v6) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                final V v5, final K k6, final V v6, final K k7, final V v7) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                final V v5, final K k6, final V v6, final K k7, final V v7, final K k8, final V v8) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                final V v5, final K k6, final V v6, final K k7, final V v7, final K k8, final V v8, final K k9, final V v9) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        map.put(k9, v9);
        return map;
    }

    static <K, V> Map<K, V> map(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                final V v5, final K k6, final V v6, final K k7, final V v7, final K k8, final V v8, final K k9, final V v9,
                                final K k10, final V v10) {
        final Map<K, V> map = new HashMap<>(16);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        map.put(k9, v9);
        map.put(k10, v10);
        return map;
    }

    static <K, V> Map<K, V> map(Consumer<Map<K, V>> init) {
        final Map<K, V> map = new HashMap<>(16);
        init.accept(map);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(@NonNull final List<K> keys, @NonNull final List<V> values) {
        if (Lang.eq(keys.size(), values.size())) {
            throw new IllegalArgumentException(Lang.format("Func map error : keys size :{},values size:{}; please check ", keys.size(), values.size()));
        }
        final Map<K, V> map = new LinkedHashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K key, final V val) {
        final Map<K, V> map = new LinkedHashMap<>(1);
        map.put(key, val);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2) {
        final Map<K, V> map = new LinkedHashMap<>(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3) {
        final Map<K, V> map = new LinkedHashMap<>(3);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4) {
        final Map<K, V> map = new LinkedHashMap<>(4);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                      final V v5) {
        final Map<K, V> map = new LinkedHashMap<>(5);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                      final V v5, final K k6, final V v6) {
        final Map<K, V> map = new LinkedHashMap<>(6);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                      final V v5, final K k6, final V v6, final K k7, final V v7) {
        final Map<K, V> map = new LinkedHashMap<>(6);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                      final V v5, final K k6, final V v6, final K k7, final V v7, final K k8, final V v8) {
        final Map<K, V> map = new LinkedHashMap<>(6);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                      final V v5, final K k6, final V v6, final K k7, final V v7, final K k8, final V v8, final K k9, final V v9) {
        final Map<K, V> map = new LinkedHashMap<>(6);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        map.put(k9, v9);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                      final V v5, final K k6, final V v6, final K k7, final V v7, final K k8, final V v8, final K k9, final V v9,
                                      final K k10, final V v10) {
        final Map<K, V> map = new LinkedHashMap<>(6);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        map.put(k8, v8);
        map.put(k9, v9);
        map.put(k10, v10);
        return map;
    }

    static <K, V> Map<K, V> linkedMap(Consumer<Map<K, V>> init) {
        final Map<K, V> map = new LinkedHashMap<>(16);
        init.accept(map);
        return map;
    }

    static <K, V> Map<K, V> cMap() {
        return new ConcurrentHashMap<>();
    }

    static <K, V> Map<K, V> cMap(final K key, final V val) {
        final Map<K, V> map = new ConcurrentHashMap<>(1);
        map.put(key, val);
        return map;
    }

    static <K, V> Map<K, V> cMap(final K k1, final V v1, final K k2, final V v2) {
        final Map<K, V> map = new ConcurrentHashMap<>(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    static <K, V> Map<K, V> cMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3) {
        final Map<K, V> map = new ConcurrentHashMap<>(3);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    static <K, V> Map<K, V> cMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4) {
        final Map<K, V> map = new ConcurrentHashMap<>(4);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    static <K, V> Map<K, V> cMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4, final K k5,
                                 final V v5) {
        final Map<K, V> map = new ConcurrentHashMap<>(5);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    static <K, V> ConcurrentHashMap<K, V> cMap(final K k1, final V v1, final K k2, final V v2, final K k3, final V v3, final K k4, final V v4,
                                               final K k5,
                                               final V v5, final K k6, final V v6) {
        final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(6);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        return map;
    }

    static <K, V> ConcurrentHashMap<K, V> cMap(Consumer<Map<K, V>> init) {
        final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(16);
        init.accept(map);
        return map;
    }

    static Collection<Integer> disjunction(List<Integer> l1, List<Integer> l2) {
        return CollUtil.disjunction(l1, l2);
    }

    @NonNull
    static <K, V> Map<K, V> concat(Map<K, V> m1, Map<K, V> m2) {
        if (m1 == null && m2 == null) {
            return map();
        }
        if (m1 == null) {
            return m2;
        }
        if (m2 == null) {
            return m1;
        }

        if (m1.size() > m2.size()) {
            m1.putAll(m2);
            return m1;
        } else {
            m2.putAll(m1);
            return m2;
        }
    }

    static <T> List<T> singleList(T t) {
        return list(t);
    }

    static <T> Set<T> singleSet(T t) {
        return set(t);
    }

    static <T> List<T> emptyList() {
        return java.util.Collections.emptyList();
    }

    static <T> Iterator<T> emptyIterator() {
        return java.util.Collections.emptyIterator();
    }

    static <T> Set<T> emptySet() {
        return java.util.Collections.emptySet();
    }

    static <T> SortedSet<T> emptySortedSet() {
        return java.util.Collections.emptySortedSet();
    }

    static <T> NavigableSet<T> emptyNavigableSet() {
        return java.util.Collections.emptyNavigableSet();
    }

    static <K, V> Map<K, V> emptyMap() {
        return java.util.Collections.emptyMap();
    }

    static <K, V> Map<K, V> emptySortedMap() {
        return java.util.Collections.emptySortedMap();
    }

    static <T> Collection<T> unmodifiable(Collection<T> coll) {
        return CollectionUtil.unmodifiable(coll);
    }
}