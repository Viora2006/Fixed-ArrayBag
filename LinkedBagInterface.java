public interface LinkedBagInterface<T> {
    int getCurrentSize(); // returns total items as int
    boolean isEmpty(); // checks if there are no elements
    boolean add(T newEntry); // creates a new node containing an entry
    T remove(); // removes first node
    boolean remove(T anEntry); // removes one instance of entry
    void clear(); // deletes all items
    int getFrequencyOf(T anEntry); // counts how many times a entry shows up
    boolean contains(T anEntry); // checks if it contains an entry
    Object[] toArray(); //makes copy of elements of bag into an array(useful for indexing)

}
