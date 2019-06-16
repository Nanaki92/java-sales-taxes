package it.nanaki92.salestaxes;

import java.util.Scanner;

import it.nanaki92.salestaxes.billing.Tax;
import it.nanaki92.salestaxes.recipit.Recipit;
import it.nanaki92.salestaxes.warehouse.Item;

public class CashRegister {
	
	private Recipit recipit;
	private Tax tax;
	
	public CashRegister(int standardTax, int importedTax) {
		this.tax = new Tax(standardTax, importedTax);
		this.recipit = new Recipit(tax);
	}
	
	public void checkoutOrder(String order) {
		Scanner scanner = new Scanner(order);  
		while (scanner.hasNextLine()) {  
		   String itemOrder = scanner.nextLine();
		   checkoutItem(itemOrder);
		}
		scanner.close();
	}
	
	private void checkoutItem(String order) {
		
		Item item = Item.parseOrderToItem(order);
		recipit.add(item);
	}
	
	public String printRecipit() {
		return recipit.printRecipit();
	}
	

}
