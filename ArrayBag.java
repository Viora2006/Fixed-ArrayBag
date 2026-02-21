import java.util.Arrays;


public class ArrayBag<T> implements BagInterface<T> {
   
    private T[] _bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int _numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;
    

    public ArrayBag (){
        this(DEFAULT_CAPACITY);  
    }

    public ArrayBag(int capacity){
        _numberOfEntries = 0;
         if(capacity<= MAX_CAPACITY){

        @SuppressWarnings("unchecked")
        T[] tempbag =(T[]) new Object [capacity];
        _bag = tempbag;
        integrityOK = true;
        return;
         }
         else{
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds maximum");
         }
         
    }

    public void checkIntegrity(){
        if(!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt");
        }
    }

   public boolean add(T newEntry){
    checkIntegrity();
    if (isFull()){
        doubleCapacity();
    }
    
    _bag[_numberOfEntries] = newEntry;
    _numberOfEntries++;
    
    
    return true;
   }
    

   public Object[] toArray(){
    @SuppressWarnings("unchecked")
    T[] _result = (T[]) new Object[_numberOfEntries];

    for(int i = 0; i <_numberOfEntries; i++){
        _result[i] = _bag[i];
    }
    return _result;


   }

   public boolean isFull(){
    return  _numberOfEntries==_bag.length;
   }
   public void printNames(){
    System.out.println("List of Items in bag: ");
    for (int i = 0; i <_numberOfEntries; i++){
        System.out.println("item " + (i+1) + ": " +_bag[i]);
    }

   }

public int getFrequencyOf(T entry){
    checkIntegrity();
    int counter = 0;
    for (int i = 0; i < _numberOfEntries; i++){
        if (entry.equals(_bag[i])) counter++;
    }
    return counter;
}



    public boolean remove(T entry){
    checkIntegrity();
    int index = getIndexOf(entry);
    T result = removeEntry(index);
    return result != null && result.equals(entry);
}
private int getIndexOf(T entry){
    int where = -1;
    boolean found = false;
    int index = 0;
    while(!found && (index < _numberOfEntries)){
        if (entry.equals(_bag[index])){
            found = true;
            where = index;
        }
        index++;
    }
    return where;
}


    public boolean isEmpty(){
        return _numberOfEntries == 0;
    }
    
    
    private T removeEntry(int givenIndex){
    T result = null;
    if (!isEmpty() && (givenIndex>=0)){
        result = _bag[givenIndex];
        _bag[givenIndex] = _bag[_numberOfEntries - 1];
        _bag[_numberOfEntries-1] = null;
        _numberOfEntries--;

    }
        return result;
}

    

    public T remove(){
        checkIntegrity();
        T result = removeEntry(_numberOfEntries-1);
        return result;
    }

    public void clear(){
        while (!isEmpty()){
            remove();
        }
    }

    public boolean contains(T entry){
        checkIntegrity();
        return getIndexOf(entry) > -1;
    }

    private void checkCapacity(int capacity){
        if (capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds maximum allowed " + MAX_CAPACITY);
        }
    }

    public int getCurrentSize(){
        return _numberOfEntries;
    }

    private void doubleCapacity(){
        int newLength = 2*_bag.length;
        checkCapacity(newLength);
        _bag = Arrays.copyOf(_bag, newLength);

    }

       

}


    
         