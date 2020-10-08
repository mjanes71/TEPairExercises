package com.techelevator;

import com.techelevator.Exceptions.NotAWholeDollarAmountException;
import com.techelevator.Machine.VendingMachine;
import com.techelevator.view.BasicUI;
import com.techelevator.view.MenuDrivenCLI;

import java.math.BigDecimal;

public class Application {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

	private static final String SUBMENU_OPTIONS_FEED_MONEY = "Feed Money";
	private static final String SUBMENU_OPTIONS_SELECT_PRODUCT = "Select Product";
	private static final String SUBMENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUBMENU_OPTIONS = {SUBMENU_OPTIONS_FEED_MONEY, SUBMENU_OPTIONS_SELECT_PRODUCT, SUBMENU_OPTIONS_FINISH_TRANSACTION};

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

	public void run() {
		boolean finished = false;
		while (!finished) {
			String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);

			if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				ui.output(myMachine.infoForPurchases());
				ui.pauseOutput();
			} else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
				handlePurchaseMenu();
			}else if (selection.equals(MAIN_MENU_OPTION_EXIT)){
				finished = true;
			}
		}
	}

	private void handlePurchaseMenu(){
		String selection = ui.promptForSelection(SUBMENU_OPTIONS);
		boolean finished =false;

		while (!finished) {
			if (selection.equals(SUBMENU_OPTIONS_FEED_MONEY)) {
				feedMoney();
				handlePurchaseMenu();
			} else if (selection.equals(SUBMENU_OPTIONS_SELECT_PRODUCT)) {
				//language to handle purchasing an object
				handlePurchaseMenu();
			} else if (selection.equals(SUBMENU_OPTIONS_FINISH_TRANSACTION)) {
				//language to display change and update current balance to zero
				finished = true;
			}
		}
	}

	private void feedMoney() {
		ui.output("Please enter a whole dollar value to deposit.");
		BigDecimal amount = ui.promptForBigDecimal();
		try {
			myMachine.getMoneyHolder().deposit(amount);
			ui.output(myMachine.getMoneyHolder().getCustomerBalance().toString());
		} catch (NotAWholeDollarAmountException e) {
			ui.output("Not a whole dollar amount. Please try again.");
		}
	}


}
