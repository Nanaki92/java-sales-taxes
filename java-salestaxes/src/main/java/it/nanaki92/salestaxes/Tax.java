package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import static it.nanaki92.salestaxes.warehouse.Category.*;
import java.math.RoundingMode;

import it.nanaki92.salestaxes.warehouse.Catalog;

public class Tax {
	private final int standardTax;
	private final Catalog catalog;
	
	public Tax(int standardTax) {
		this.standardTax = standardTax;
		this.catalog = new Catalog();
	}
	
	public BigDecimal calculateTaxOnItem(Item item) {
		int taxRate = taxRatePerItem(item);
		BigDecimal price = item.getNetPrice();
		
		BigDecimal taxOnItem = price.divide(new BigDecimal(100)).multiply(new BigDecimal(taxRate));
		taxOnItem = taxOnItem.setScale(2, RoundingMode.HALF_UP);
		
		return taxOnItem;
	}
	
	private int taxRatePerItem(Item item) {
		String productName = item.getProductName();
		if (isTaxed(productName)) {
			return standardTax;
		}
		return 0;
	}
	
	private boolean isTaxed(String productName) {
		if (catalog.getCategory(productName) == OTHER) {
			return true;
		}
		return false;
	}
	
}
