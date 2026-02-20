public interface BagInterface<T>{


    boolean add(T newEntry); // Adds a new entry to bag(returns true if item was added successfully)

    Object[] toArray(); // Returns all elements in the bag as an array(useful if you need to use indexs)

    boolean isEmpty(); //checks if the bag is empty, returns true if no elements

    int getFrequencyOf(T entry); //returns how many times a specific entry appears in bag

    boolean remove(T entry); //removes one occurence of a specific entry(returns true if successful)

    T remove(); // removes unspecified item from bag, returns true if successful

    void clear();// removes all items from bag

    boolean contains(T entry);//checks to see if entry exists in bag(returns true if exists)

    int getCurrentSize(); // returns the current number of items in the bag

}