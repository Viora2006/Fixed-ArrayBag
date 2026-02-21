import java.util.Scanner;

public class LinkedShoppingProgram {
      private static final int MAX_QUANTITY = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my store, here's the menu:");

        Item[] _store = LinkedStoreUtility.readFile(); 

        LinkedBagInterface<Item> shoppingbag = new LinkedBag<>();
        LinkedBagInterface<Item> wishbag = new LinkedBag<>();

        while (true) {
            System.out.println("H ->  Print out the Help Message");
            System.out.println("A ->  Add item(s) with a given RFID # followed by the quantity");
            System.out.println("R ->  Remove item(s) with a given RFID # followed by the quantity");
            System.out.println("C ->  Combine the current bag with wishbag");
            System.out.println("D ->  Show all items in shopping bag with a total price");
            System.out.println("F ->  Shows all items in wish bag with a total price");
            System.out.println("G ->  exit");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Invalid input, press H for help");
                continue;
            }

            String[] parts = input.split("\\s+"); // ai assisted

            char command = parts[0].toUpperCase().charAt(0);

            
            if (!LinkedStoreUtility.VerifyCommandInput(command, parts)) {
                continue;
            }

            switch (command) {

                case 'A': {
                    int rfidA = Integer.parseInt(parts[1]);
                    Item verify = LinkedStoreUtility.findByRfid(_store, rfidA);
                    if (verify==null){
                        System.out.println("Item does not exist within store");
                        break;
                    }
                    int qtyA = Integer.parseInt(parts[2]);

                    if (qtyA <= 0) {
                        System.out.println("Quantity must be greater than 0");
                        break;
                    }
                    if (qtyA > MAX_QUANTITY) {
                        System.out.println("Max quantity to add at a time is " + MAX_QUANTITY);
                        break;
                    }

                    Item found = LinkedStoreUtility.findByRfid(_store, rfidA);
                    if (found == null) {
                        System.out.println("Item with RFID " + rfidA + " does not exist within the store");
                        break;
                    }

                    LinkedStoreUtility.printBagChoicesToAdd();
                    String whichBag = scanner.nextLine().trim();

                    if (whichBag.equalsIgnoreCase("S")) {
                        for (int i = 0; i < qtyA; i++) {
                            shoppingbag.add(found);
                        }
                        System.out.println(found + " (" + qtyA + ") placed in your shopping bag");
                    } else if (whichBag.equalsIgnoreCase("W")) {
                        for (int i = 0; i < qtyA; i++) {
                            wishbag.add(found);
                        }
                        System.out.println(found + " (" + qtyA + ") placed in your wish bag");
                    } else {
                        System.out.println("invalid bag");
                    }
                    break;
                }

                case 'R': {
                    int rfidR = Integer.parseInt(parts[1]);
                    Item verify = LinkedStoreUtility.findByRfid(_store, rfidR);
                    if (verify==null){
                        System.out.println("Item does not exist within store");
                        break;
                    }
                    int qtyR = Integer.parseInt(parts[2]);

                    if (qtyR <= 0) {
                        System.out.println("Quantity must be greater than 0");
                        break;
                    }
                    if (qtyR > MAX_QUANTITY) {
                        System.out.println("Max quantity to remove at a time is " + MAX_QUANTITY);
                        break;
                    }

                    LinkedStoreUtility.printBagChoicesToRemove();
                    String whichbagforremoval = scanner.nextLine().trim();

                    if (whichbagforremoval.equalsIgnoreCase("S")) {
                        int removedAmount = 0;

                        for (int i = 0; i < qtyR; i++) {
                            Item itemtoremove = LinkedStoreUtility.findByRfidForBag(shoppingbag, rfidR);

                            if (itemtoremove != null) {
                                shoppingbag.remove(itemtoremove);
                                removedAmount++;
                            } else {
                                System.out.println("Not enough items to remove (existing items were removed)");
                                break;
                            }
                        }

                        if (removedAmount > 0) {
                            System.out.println("You removed " + removedAmount + " item(s) from your shopping bag");
                        }

                    } else if (whichbagforremoval.equalsIgnoreCase("W")) {
                        int removedAmount = 0;

                        for (int i = 0; i < qtyR; i++) {
                            Item itemtoremove = LinkedStoreUtility.findByRfidForBag(wishbag, rfidR);

                            if (itemtoremove != null) {
                                wishbag.remove(itemtoremove);
                                removedAmount++;
                            } else {
                                System.out.println("Not enough items to remove (existing items were removed)");
                                break;
                            }
                        }

                        if (removedAmount > 0) {
                            System.out.println("You removed " + removedAmount + " item(s) from your wish bag");
                        }

                    } else {
                        System.out.println("invalid bag");
                    }
                    break;
                }

                case 'H':
                    LinkedStoreUtility.printHelp();
                    break;

                case 'C':
                    System.out.println("Combine bags:");
                    for (Object obj : wishbag.toArray()) {
                        Item item = (Item) obj;
                        if (item != null) shoppingbag.add(item);
                    }
                    wishbag.clear();
                    System.out.println("New combined bag shown below:");
                    LinkedStoreUtility.PrintBagAndPrice(shoppingbag);
                    break;

                case 'D':
                    System.out.println("Shopping bag shown below:");
                    LinkedStoreUtility.PrintBagAndPrice(shoppingbag);
                    System.out.println("Checkout: Total price is $" + LinkedStoreUtility.addPrices(shoppingbag));
                    System.out.println();
                    break;

                case 'F':
                    System.out.println("Wish bag shown below:");
                    LinkedStoreUtility.PrintBagAndPrice(wishbag);
                    System.out.println("Checkout: Total price is $" + LinkedStoreUtility.addPrices(wishbag));
                    System.out.println();
                    break;

                case 'G':
                    System.out.println("Goodbye, please come again!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid input. Type H if you need assistance");
            }
        }
    }
}

