import java.util.Scanner;
import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
    

   public T[] toArray(){
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
    for (int i = 0; i<_numberOfEntries; i++){
        if(entry.equals(_bag[i])){
            counter++;
        }

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

        public static Item[] readFile() {
        String file = "store.csv";
        Item[] StoreArray = new Item[30];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean first = true;
            int index = 0;

            while ((line = br.readLine()) != null && index < StoreArray.length) {
                if (first) {
                    first = false;
                    continue;
                }

                String[] parts = line.split(",");
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                String color = parts[2].trim();
                String size = parts[3].trim();
                int rfid = Integer.parseInt(parts[4].trim());

                StoreArray[index++] = new Item(name, price, color, size, rfid);
            }
             for (int i = 0; i < index; i++){
                    System.out.println(StoreArray[i]);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return StoreArray;
    }
            
    public static void printHelp(){
        System.out.println("Example inputs shown Below:");
        System.out.println("A 5 1");
        System.out.println("Add|RFID#|quantity -> adds item with RFID # 5 for a quantity of 1");
        System.out.println();
        System.out.println("R 2 1");
        System.out.println("Removes|RFID#|quantity -> removes item with RFID # 2 for a quantity of 1");
        System.out.println();
        System.out.println("C");
        System.out.println("Combines shopping bag with wishbag");
        System.out.println();
        System.out.println("D");
        System.out.println("Displays all items in shopping bag");
        System.out.println();
        System.out.println("F");
        System.out.println("Displays all items in wishbag");


    }

    public static Item findByRfid(Item[] _store, int rfid){
        for (Item item : _store){
            if(item!=null && item.getRfid()==rfid){
                return item;
                
                


            }
        }
        return null;
    }
   

    public static void printBagChoicesToAdd(){
        System.out.println("Which bag would you like to store the item(s) in");
        System.out.println("S -> shopping bag");
        System.out.println("W -> wish bag");
    }

    public static Item findByRfidForBag (BagInterface<Item> bag, int rfid){
        for (Object obj: bag.toArray()){
            Item item = (Item) obj;
            if(item.getRfid()==rfid){
                return item;
            }
        }
        return null;
    }

    public static void printBagChoicesToRemove(){
        System.out.println("Which bag would you like to remove the item(s) from");
        System.out.println("S -> shopping bag");
        System.out.println("W -> wish bag");
    }

     public static boolean verifyInputLength(String[] parts){
        if (parts.length < 3 || parts == null){
        System.out.println("Input Invalid, press H for help");  
        }
        return true;
      
    }

    public static boolean VerifyCommandInput(char command, String [] parts){
        
        if(command == 'H' || command == 'C' || command == 'D'|| command == 'F' || command == 'G'){
            if (parts.length != 1){
                System.out.println("This feature only takes a one letter input, press H for help");
                return false;
            }
            return true;
        }
        if (command == 'A' || command == 'R'){
            if(parts.length< 3){
                System.out.println("This feature takes a 3 letter input, press H for help");
                return false;
            }
            return true;
        }
        System.out.println("Invalid input, Press H for help"); 
        return false;




    }
}


    
         