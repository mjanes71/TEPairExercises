package com.techelevator.Machine;

import java.math.BigDecimal;

public class VendingMachine {

    //vending machine has instances of cashbox and inventory
    private CashBox myCashBox = new CashBox();
    private Inventory myInventory = new Inventory();

    //constructor
    public VendingMachine() {
        myInventory.createInventory("inventory.txt");
    }
    //getters, setters, and helper methods
    public CashBox getMyCashBox() {
        return myCashBox;
    }

    public String infoForPurchases(){
       return myInventory.displayInventory();
    }

    public BigDecimal priceByCode(String itemCode){
        return myInventory.getAPrice(itemCode);
    }

    //purchase method used by purchase menu
    public String purchase(String itemCode){
        BigDecimal another0 = new BigDecimal("0.00");
        if(myCashBox.getCustomerBalance().compareTo(another0) == 0){
           return "Error: Zero balance.";
        }else if (!myInventory.checkForItemCode(itemCode)) {
            return "Error: please select an item from the available options.";
        }
        else if (priceByCode(itemCode).compareTo(myCashBox.getCustomerBalance()) > 0){
            return "Error: Deposit more money to purchase this item";
        }else if (myInventory.itemSoldOut(itemCode)){
            return "Item sold out, choose something else.";
        } else {
            myCashBox.setCustomerBalance(myCashBox.getCustomerBalance().subtract(priceByCode(itemCode)));
            myInventory.setQuantity(itemCode);
        }
        return myInventory.getItem(itemCode).getSound() + "\n" + "You have " + myCashBox.getCustomerBalance() + " left to spend.";
    }



}
