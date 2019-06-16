package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.nanaki92.salestaxes.billing.Tax;
import it.nanaki92.salestaxes.warehouse.Item;

public class CashRegister {
	
	private Item item;
	private Tax tax;
	
	public CashRegister(int standardTax, int importedTax) {
		tax = new Tax(standardTax, importedTax);
	}
	
	public void checkoutItem(String order) {
		
		item = Item.parseOrderToItem(order);
	}
	
	public String printRecipit() {
			
		BigDecimal taxOnItem = tax.calculateTaxOnItem(item);
		
		BigDecimal grossPrice = item.getNetPrice().add(taxOnItem);
		grossPrice = grossPrice.setScale(2, RoundingMode.HALF_UP);
		
		
		String recipit = item.getQuantity() + " " + importedPrint(item) + item.getProductName() + ": " + grossPrice + "\n"
				+ "Sales Taxes: " + taxOnItem + "\n"
				+ "Total: " + grossPrice;
				
		return recipit;
	}
	
	private String importedPrint(Item item) {
		if (item.isImported()) {
			return "imported ";
		}
		return "";
	}

}
