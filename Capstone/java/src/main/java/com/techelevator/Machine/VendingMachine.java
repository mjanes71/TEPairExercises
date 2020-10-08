package com.techelevator.Machine;

import java.math.BigDecimal;

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

    public BigDecimal priceByCode(String itemCode){
        return myInventory.getAPrice(itemCode);
    }

    public String purchase(String itemCode){
        BigDecimal another0 = new BigDecimal("0.00");
        if(moneyHolder.getCustomerBalance().compareTo(another0) == 0){
           return "Error: Zero balance.";
        } else if (priceByCode(itemCode).compareTo(moneyHolder.getCustomerBalance()) > 0){
            return "Error: Deposit more money to purchase this item";
        }else if (myInventory.itemSoldOut(itemCode)){
            return "Item sold out, choose something else.";
        } else {
            moneyHolder.setCustomerBalance(moneyHolder.getCustomerBalance().subtract(priceByCode(itemCode)));
            myInventory.setQuantity(itemCode);

        }
        return "You have " + moneyHolder.getCustomerBalance() + " left to spend.";
    }



}
