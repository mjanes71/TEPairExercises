package com.techelevator.Machine;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    //inventory map: key is the item code, value is the item object
    private Map<String, Item> inventory = new HashMap<>();

    //getters, setters, and helper methods
    public BigDecimal getAPrice(String itemCode) {
        Item itemToFind = inventory.get(itemCode);
        return itemToFind.getPriceAsDecimal();
    }

    public void setQuantity(String itemCode) {
        Item itemToUpdate = inventory.get(itemCode);
        itemToUpdate.setQuantity(itemToUpdate.getQuantity() - 1);
    }

    public boolean checkForItemCode(String itemCode) {
        return inventory.containsKey(itemCode);
    }

    public Item getItem(String itemCode) {
        return inventory.get(itemCode);
    }

    //create inventory map from a formatted txt file
    public void createInventory(String inventoryFile) {
        Path myFile = Paths.get(inventoryFile);
        try (Scanner reader = new Scanner(myFile)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] itemInfo = line.split("\\|");
                Item myItem = new Item(itemInfo[0], itemInfo[1], new BigDecimal(itemInfo[2]), itemInfo[3]);
                inventory.put(itemInfo[0], myItem);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    //displays inventory at purchase menu
    public String displayInventory() {

        String inventoryUpdate = "";
        for (Map.Entry<String, Item> map : inventory.entrySet()) {
            if (map.getValue().getQuantity() == 0) {
                inventoryUpdate += map.getKey() + " | " + "SOLD OUT" + "\n";
            } else {
                inventoryUpdate += map.getKey() + " | " + map.getValue().getName() + " | " + map.getValue().getPrice() + "\n";
            }
        }
        return inventoryUpdate;
    }

    //displays sales report at sales report main menu option
    public String displayInventoryForSalesReport() {
        String salesUpdates = "";
        for (Map.Entry<String, Item> map : inventory.entrySet()) {
            salesUpdates += map.getValue().getName() + " | " + (5 - (map.getValue().getQuantity())) + "\n";
        }
        return salesUpdates;
    }

    //item sold out - used in display inventory
    public boolean itemSoldOut(String itemCode) {
        Item itemToCheck = inventory.get(itemCode);
        return itemToCheck.getQuantity() == 0;
    }
}








