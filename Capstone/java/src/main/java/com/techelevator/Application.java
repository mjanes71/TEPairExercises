package com.techelevator;

import com.techelevator.Exceptions.NotAWholeDollarAmountException;
import com.techelevator.Machine.VendingMachine;
import com.techelevator.view.BasicUI;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Application {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

    private static final String SUBMENU_OPTIONS_FEED_MONEY = "Feed Money";
    private static final String SUBMENU_OPTIONS_SELECT_PRODUCT = "Select Product";
    private static final String SUBMENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] SUBMENU_OPTIONS = {SUBMENU_OPTIONS_FEED_MONEY, SUBMENU_OPTIONS_SELECT_PRODUCT, SUBMENU_OPTIONS_FINISH_TRANSACTION};

    // our instance of vending machine
    private VendingMachine myMachine = new VendingMachine();

    private final BasicUI ui;

    public Application(BasicUI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        BasicUI cli = new MenuDrivenCLI();
        Application application = new Application(cli);
        application.run();
    }

    //vendng machine main menu
    public void run() {
        boolean finished = false;
        while (!finished) {
            String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);

            if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                ui.output(myMachine.infoForPurchases());
                ui.pauseOutput();
            } else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
                handlePurchaseMenu();
            } else if (selection.equals(MAIN_MENU_OPTION_EXIT)) {
                finished = true;
            } else if (selection.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                ui.output(myMachine.getMyInventory().displayInventoryForSalesReport() + "\n" + "**Total Sales** $" + myMachine.getMySalesReports().getMachineTotalSales());
            }
        }
    }

    //vending machine purchase menu
    private void handlePurchaseMenu() {
        String selection = ui.promptForSelection(SUBMENU_OPTIONS);
        boolean done = false;

        while (!done) {
            if (selection.equals(SUBMENU_OPTIONS_FEED_MONEY)) {
                feedMoney();
                selection = ui.promptForSelection(SUBMENU_OPTIONS);
            } else if (selection.equals(SUBMENU_OPTIONS_SELECT_PRODUCT)) {
                ui.output(myMachine.infoForPurchases());
                ui.output("Please enter code of item you would like to purchase: ");
                String itemCode = ui.promptForString();
                ui.output(myMachine.purchase(itemCode));
                selection = ui.promptForSelection(SUBMENU_OPTIONS);
            } else if (selection.equals(SUBMENU_OPTIONS_FINISH_TRANSACTION)) {
                //language to display change and update current balance to zero
                ui.output(myMachine.giveChange());
                done = true;
            }
        }
    }

    //vending machine deposit helper method - connects to cashbox
    private void feedMoney() {
        ui.output("Please enter a whole dollar value to deposit.");
        try {
            BigDecimal amount = ui.promptForBigDecimal();
            BigDecimal amountReFormatted = amount.add(new BigDecimal("0.00")); //for formatting
            try {
                myMachine.getMyCashBox().deposit(amount);
                myMachine.getMySalesReports().addToTransactionLog(" FEED MONEY: $" + amountReFormatted + " $" + myMachine.getMyCashBox().getCustomerBalance());
                ui.output("Your balance is: $" + myMachine.getMyCashBox().getCustomerBalance().toString());
            } catch (NotAWholeDollarAmountException e) {
                ui.output("Not a whole dollar amount. Please try again.");
            }
        } catch (NumberFormatException n) {
            ui.output("Not a dollar amount. Please try again.");
        }
    }
}
