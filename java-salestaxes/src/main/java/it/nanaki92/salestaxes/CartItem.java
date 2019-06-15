package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartItem {
	
	private final String productName;
	private final String quantity;
	private final BigDecimal netPrice;
	
	public CartItem(String productName, String quantity, BigDecimal netPrice) {
		this.productName = productName;
		this.quantity = quantity;
		this.netPrice = netPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	
	public static CartItem parseOrderToItem(String order) {
		
		String patternToGetQuantityNamePrice = "^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2})";
		Pattern pattern = Pattern.compile(patternToGetQuantityNamePrice);
		
		Matcher matcher = pattern.matcher(order);
		matcher.matches();
		
		String quantity = matcher.group(1);
		String productName = matcher.group(2);
		BigDecimal netPrice = new BigDecimal(matcher.group(3));
		
		return new CartItem(productName, quantity, netPrice);
	}
	
}
