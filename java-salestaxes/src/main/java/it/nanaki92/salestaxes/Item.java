package it.nanaki92.salestaxes;

import java.math.BigDecimal;

public class Item {
	
	private final String productName;
	private final String quantity;
	private final BigDecimal neatPrice;
	
	public Item(String productName, String quantity, BigDecimal neatPrice) {
		this.productName = productName;
		this.quantity = quantity;
		this.neatPrice = neatPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public BigDecimal getNeatPrice() {
		return neatPrice;
	}
	
}
