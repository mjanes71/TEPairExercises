package com.techelevator.Machine;

public class VendingMachine {

    private CashBox moneyHolder = new CashBox();
    private Inventory myInventory = new Inventory();

    public VendingMachine() {
        myInventory.createInventory("inventory.txt");
    }
    public CashBox getMoneyHolder() {
        return moneyHolder;
    }

    public String infoForPurchases(){
       return myInventory.displayInventory();
    }




}
