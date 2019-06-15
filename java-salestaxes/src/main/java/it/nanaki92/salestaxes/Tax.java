package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax {
	private final int standardTax;
	
	public Tax(int standardTax) {
		this.standardTax = standardTax;
	}
	
	public BigDecimal calculateTaxOnItem(Item item) {
		int taxRate = taxRatePerItem(item);
		BigDecimal price = item.getNetPrice();
		
		BigDecimal taxOnItem = price.divide(new BigDecimal(100)).multiply(new BigDecimal(taxRate));
		taxOnItem = taxOnItem.setScale(2, RoundingMode.HALF_UP);
		
		return taxOnItem;
	}
	
	private int taxRatePerItem(Item item) {
		if (item.getProductName().contains("book")) {
			return 0;
		}
		return standardTax;
	}
	
}
