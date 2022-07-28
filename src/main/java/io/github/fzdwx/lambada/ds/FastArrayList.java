package io.github.fzdwx.lambada.ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * todo
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/7/28 20:22
 */
public class FastArrayList<E> implements List<E> {

    private ArrayList<E> internal;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public FastArrayList() {
        this.internal = new ArrayList<>();
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public FastArrayList(final Collection<? extends E> c) {
        this.internal = new ArrayList<>(c);
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public FastArrayList(int initialCapacity) {
        this.internal = new ArrayList<>(initialCapacity);
    }

    @Override
    public int size() {
        return internal.size();
    }

    @Override
    public boolean isEmpty() {
        return internal.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return internal.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return internal.iterator();
    }

    @Override
    public Object[] toArray() {
        return internal.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return internal.toArray(a);
    }

    @Override
    public boolean add(final E e) {
        return internal.add(e);
    }

    @Override
    public boolean remove(final Object o) {
        return internal.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return internal.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return internal.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> c) {
        return internal.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return internal.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return internal.removeAll(c);
    }

    @Override
    public void clear() {
        internal.clear();
    }

    @Override
    public E get(final int index) {
        return internal.get(index);
    }

    @Override
    public E set(final int index, final E element) {
        return internal.set(index, element);
    }

    @Override
    public void add(final int index, final E element) {
        internal.add(index, element);
    }

    @Override
    public E remove(final int index) {
        // todo fast
        return internal.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return internal.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return internal.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return internal.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return internal.listIterator(index);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        return internal.subList(fromIndex, toIndex);
    }
}