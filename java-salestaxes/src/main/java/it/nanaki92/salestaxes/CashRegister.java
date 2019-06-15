package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CashRegister {
	
	private CartItem cartItem;
	
	public void checkoutItem(String order) {
		
		cartItem = CartItem.parseOrderToItem(order);
		
	}
	
	private int taxRatePerItem(CartItem cartItem) {
		if (cartItem.getProductName().contains("book")) {
			return 0;
		}
		return 10;
	}
	
	private BigDecimal calculateTax(CartItem cartItem) {
		int taxRate = taxRatePerItem(cartItem);
		BigDecimal price = cartItem.getNetPrice();
		
		BigDecimal taxOnItem = price.divide(new BigDecimal(100)).multiply(new BigDecimal(taxRate));
		taxOnItem = taxOnItem.setScale(2, RoundingMode.HALF_UP);
		
		return taxOnItem;
	}
	
	
	public String printRecipit() {
			
		BigDecimal taxOnItem = calculateTax(cartItem);
		
		BigDecimal grossPrice = cartItem.getNetPrice().add(taxOnItem);
		grossPrice = grossPrice.setScale(2, RoundingMode.HALF_UP);
		
		
		String recipit = cartItem.getQuantity() + " " + cartItem.getProductName() + ": " + grossPrice + "\n"
				+ "Sales Taxes: " + taxOnItem + "\n"
				+ "Total: " + grossPrice;
				
		return recipit;
	}

}
