package com.techelevator.Machine;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, Item> inventory = new HashMap<>();

    public void createInventory(String inventoryFile){
        Path myFile = Paths.get(inventoryFile);
        try(Scanner reader = new Scanner(myFile)){
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] itemInfo = line.split("\\|");
                Item myItem = new Item(itemInfo[0], itemInfo[1], new BigDecimal(itemInfo[2]), itemInfo[3]);
                inventory.put(itemInfo[0], myItem);

            }

        }catch (IOException e){
            System.out.println("File not found");
        }
    }

    public String displayInventory() {

        String inventoryUpdate = "";
        for (Map.Entry<String, Item> map : inventory.entrySet()) {
            if(map.getValue().getQuantity() == 0){
                inventoryUpdate += map.getKey() + " | " + "SOLD OUT" + "\n";
            }else {
                inventoryUpdate +=map.getKey() + " | " + map.getValue().getName() + " | " + map.getValue().getPrice() + "\n";
            }
        }
        return inventoryUpdate;
    }
    public BigDecimal getAPrice(String itemCode){
         Item itemToFind = inventory.get(itemCode);
         return itemToFind.getPriceAsDecimal();
    }

    public void setQuantity(String itemCode){
        Item itemToUpdate = inventory.get(itemCode);
        itemToUpdate.setQuantity(itemToUpdate.getQuantity()-1);
    }

    public boolean itemSoldOut(String itemCode){
        Item itemToCheck = inventory.get(itemCode);
        if(itemToCheck.getQuantity() == 0){
            return true;
        }
        return false;
    }

    }








//? Would it make sense to create and item class that stores all the info and then store the item name in a map as a key, and the value to the the item object? (similar to project managment software)
//method to access the items (name, quantity available, possibly cost?) - map?

//sold out

//

//private static final int STARTING_QUANTITY = 5;
//private Map<String,Integer> inventory = new HashMap<>();
//private Path inventoryFile;
//
//public Inventory(String filePath){
//    inventoryFile = Paths.get(filePath);
//}
//
//public Map<String, Integer> getInventory(){
//    try(Scanner reader = new Scanner(inventoryFile)){
//}catch (IOException e){
//
//    }
//    }
