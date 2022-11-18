public interface Set<T> {
    int size();

    boolean isEmpty();

    boolean contains(T object);

    boolean add(T element);

    boolean remove(T element);

    void clear();
}