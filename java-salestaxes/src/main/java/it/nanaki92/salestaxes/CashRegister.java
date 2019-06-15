package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashRegister {
	
	private Item item;
	private Tax tax;
	
	public CashRegister(int standardTax) {
		tax = new Tax(standardTax);
	}
	
	public void checkoutItem(String order) {
		
		item = Item.parseOrderToItem(order);
	}
	
	public String printRecipit() {
			
		BigDecimal taxOnItem = tax.calculateTaxOnItem(item);
		
		BigDecimal grossPrice = item.getNetPrice().add(taxOnItem);
		grossPrice = grossPrice.setScale(2, RoundingMode.HALF_UP);
		
		
		String recipit = item.getQuantity() + " " + item.getProductName() + ": " + grossPrice + "\n"
				+ "Sales Taxes: " + taxOnItem + "\n"
				+ "Total: " + grossPrice;
				
		return recipit;
	}

}
