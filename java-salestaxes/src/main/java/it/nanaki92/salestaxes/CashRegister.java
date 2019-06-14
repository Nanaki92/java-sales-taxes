package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CashRegister {
	
	private Item item;
	
	public void checkoutItem(String order) {
		
		item = parseOrder(order);
		
	}

	private Item parseOrder(String order) {
		
		String patternToGetQuantityNamePrice = "^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2})";
		Pattern pattern = Pattern.compile(patternToGetQuantityNamePrice);
		
		Matcher matcher = pattern.matcher(order);
		matcher.matches();
		
		String quantity = matcher.group(1);
		String productName = matcher.group(2);
		BigDecimal neatPrice = new BigDecimal(matcher.group(3));
		
		return new Item(productName, quantity, neatPrice);
	}
	
	private int taxRatePerItem(Item item) {
		if (item.getProductName().contains("book")) {
			return 0;
		}
		
		return 10;
	}
	
	private BigDecimal calculateTax(int taxRate) {
		BigDecimal price = item.getNeatPrice();
		BigDecimal taxOnItem = price.divide(new BigDecimal(100)).multiply(new BigDecimal(taxRate));
		
		return taxOnItem;
	}
	
	public String printRecipit() {
		
		BigDecimal taxOnItem = calculateTax(taxRatePerItem(item));
		taxOnItem = taxOnItem.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal grossPrice = item.getNeatPrice().add(taxOnItem);
		grossPrice = grossPrice.setScale(2, RoundingMode.HALF_UP);
		
		
		String recipit = item.getQuantity() + " " + item.getProductName() + ": " + grossPrice + "\n"
				+ "Sales Taxes: " + taxOnItem + "\n"
				+ "Total: " + grossPrice;
				
		return recipit;
	}

}
