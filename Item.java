 public class Item {
        String name;
        double price;
        String color;
        String size;
        private int rfid;

        public Item(String name, double price, String color, String size, int rfid){
            this.name = name;
            this.price = price;
            this.color = color;
            this.size = size;
            this.rfid = rfid;
        }

public String toString (){
    return name + " | " + price + " | " + color + " | " + size + " | " + rfid ;
}



 public int getRfid(){
        return rfid;  // encapcsulation to avoid data being misused
    }
    }

