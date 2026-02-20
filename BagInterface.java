public interface BagInterface<T>{


    boolean add(T newEntry);

    T[] toArray();

    boolean isEmpty();

    int getFrequencyOf(T entry);

    boolean remove(T entry);

    T remove();

    void clear();

    boolean contains(T entry);

    int getCurrentSize();

}