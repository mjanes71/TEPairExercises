package com.techelevator.Machine;

import com.techelevator.reports.SalesReports;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine {

    //vending machine has instances of cashbox, salesreport and inventory
    private CashBox myCashBox = new CashBox();
    private static Inventory myInventory = new Inventory();
    private SalesReports mySalesReports = new SalesReports();

    //constructor
    public VendingMachine() {
        myInventory.createInventory("inventory.txt");
    }

    //getters, setters, and helper methods
    public CashBox getMyCashBox() {
        return myCashBox;
    }

    public String infoForPurchases() {
        return myInventory.displayInventory();
    }

    public BigDecimal priceByCode(String itemCode) {
        return myInventory.getAPrice(itemCode);
    }

    public SalesReports getMySalesReports() {
        return mySalesReports;
    }

    public Inventory getMyInventory() {
        return myInventory;
    }

    //purchase method used by purchase menu
    public String purchase(String itemCode) {
        BigDecimal another0 = new BigDecimal("0.00");
        if (myCashBox.getCustomerBalance().compareTo(another0) == 0) {
            return "Error: Zero balance.";
        } else if (!myInventory.checkForItemCode(itemCode)) {
            return "Error: please select an item from the available options.";
        } else if (priceByCode(itemCode).compareTo(myCashBox.getCustomerBalance()) > 0) {
            return "Error: Deposit more money to purchase this item";
        } else if (myInventory.itemSoldOut(itemCode)) {
            return "Item sold out, choose something else.";
        } else {
            myCashBox.setCustomerBalance(myCashBox.getCustomerBalance().subtract(priceByCode(itemCode)));
            mySalesReports.addToTransactionLog(" " + myInventory.getItem(itemCode).getName() + " " + itemCode + " $" + myInventory.getAPrice(itemCode) + " $" + myCashBox.getCustomerBalance());
            myInventory.setQuantity(itemCode);
            mySalesReports.setMachineTotalSales(myInventory.getAPrice(itemCode));
        }
        return myInventory.getItem(itemCode).getSound() + "\n" + "You have " + myCashBox.getCustomerBalance() + " left to spend.";
    }

    //set customer balance to zero and say "returned ___ change"
    public String giveChange() {
        String change = "Your change is " + myCashBox.getCustomerBalance() + "\n" + "Which is " + makeChange(myCashBox.getCustomerBalance());
        mySalesReports.addToTransactionLog(" GIVE CHANGE: $" + myCashBox.getCustomerBalance() + " " + "$0.00" + "\n");
        myCashBox.setCustomerBalance(new BigDecimal("0.00"));
        return change;
    }

    //calculate how to make change out of quarters, dimes, and nickels
    public String makeChange(BigDecimal customerChange) {
        BigDecimal total = customerChange;
        BigDecimal quarters = new BigDecimal("0");
        BigDecimal dimes = new BigDecimal("0");
        BigDecimal nickels = new BigDecimal("0");

        quarters = total.divide(new BigDecimal("0.25")).setScale(0, RoundingMode.DOWN);
        total = total.subtract(quarters.multiply(new BigDecimal("0.25")));

        dimes = total.divide(new BigDecimal("0.10")).setScale(0, RoundingMode.DOWN);
        total = total.subtract(dimes.multiply(new BigDecimal("0.10")));
        nickels = total.divide(new BigDecimal("0.05")).setScale(0, RoundingMode.DOWN);

        return quarters + " Quarter(s), " + dimes + " Dime(s), and " + nickels + " Nickel(s).";
    }
}
