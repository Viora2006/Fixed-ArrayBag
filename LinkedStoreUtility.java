import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinkedStoreUtility {
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

    public static Item findByRfidForBag (LinkedBagInterface<Item> bag, int rfid){
        for (Object obj: bag.toArray()){
            if (obj == null) continue;
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

    

    public static boolean VerifyCommandInput(char command, String [] parts){
        
        if ((parts[0].length() != 1) ){
            System.out.println("Invalid input, Commands such as A, B, C, etc must be one letter, press H for help");
            return false;

        }

        if(command == 'H' || command == 'C' || command == 'D'|| command == 'F' || command == 'G'){
            if (parts.length != 1){
                System.out.println("This feature only takes a one letter input, press H for help");
                return false;
            }
            return true;
        }
        if (command == 'A' || command == 'R'){
            if(parts.length!= 3){
                System.out.println("This feature takes a 3 letter input, press H for help");
                return false;
            }

            try {
                Integer.parseInt(parts[1]);
                Integer.parseInt(parts[2]);
            }
            catch (NumberFormatException e) {
                System.out.println("RFID and quantity must be integers.");
                return false;
            }
            return true;
        }
        System.out.println("Invalid input, Press H for help"); 
        return false;




    }

    public static double addPrices(LinkedBagInterface<Item> bag){
       double price = 0.0;  
        if (bag.isEmpty()){
            return price;
        }
        
        for (Object obj: bag.toArray()){
           Item item = (Item) obj;
          if(item!= null) {price +=item.getPrice();}
        }
        return Math.round(price * 100.0) / 100.0;

    }

    public static void PrintBagAndPrice(LinkedBagInterface<Item> bag){
        Object[] array = bag.toArray();
        if (array.length == 0){System.out.println("Bag is empty");
            return;
        }
        System.out.println("Item name        Price    Color Size  id  qty");
        
    for (int i = 0; i < array.length; i++){
    Item item = (Item) array[i];
    if (item == null) continue;

    boolean alreadyPrinted = false;

    for (int j = 0; j < i; j++){
        Item prev = (Item) array[j];
        if (prev != null && prev.getRfid() == item.getRfid()){
            alreadyPrinted = true;
            break;
        }
    }

    if (!alreadyPrinted){
        int count = 0;

        for (int k = 0; k < array.length; k++){
            Item current = (Item) array[k];
            if (current != null && current.getRfid() == item.getRfid()){
                count++;
            }
        }

        System.out.println(item + " |  " + count);
}
}
}

}
