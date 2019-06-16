package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {
	
	
	private final String productName;
	private final String quantity;
	private final BigDecimal netPrice;
	private final boolean imported;
	
	public Item(String productName, String quantity, BigDecimal netPrice, boolean imported) {
		this.productName = productName;
		this.quantity = quantity;
		this.netPrice = netPrice;
		this.imported = imported;
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
	
	public boolean isImported() {
		return imported;
	}
	
	public static Item parseOrderToItem(String order) {
		
		String patternToGetQuantityNamePrice = "^(\\d)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2})";
		Pattern pattern = Pattern.compile(patternToGetQuantityNamePrice);
		
		Matcher matcher = pattern.matcher(order);
		matcher.matches();
		
		String quantity = matcher.group(1);
		String productName = matcher.group(2);
		BigDecimal netPrice = new BigDecimal(matcher.group(3)); 

		boolean imported = itemImported(productName);
		if (imported) {
			productName = clearImportedProductName(productName);
		}
		
		return new Item(productName, quantity, netPrice, imported);
	}
	
	private static boolean itemImported(String productName) {
		return productName.contains("imported");
	}
	
	private static String clearImportedProductName(String productName) {
		productName = productName.replace("imported", "");
        productName = productName.replace("  ", " ");
        return productName.trim();
	}

	
}
