package it.nanaki92.salestaxes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.nanaki92.salestaxes.warehouse.Catalog;
import it.nanaki92.salestaxes.warehouse.Category;

public class Item {

	private final String productName;
	private final String quantity;
	private final BigDecimal netPrice;
	private final boolean imported;
	private final Category category;
	
	public Item(String productName, String quantity, BigDecimal netPrice, boolean imported, Category category) {		
		this.productName = productName;
		this.quantity = quantity;
		this.netPrice = netPrice;
		this.imported = imported;
		this.category = category;
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
	
	public Category getCategory() {
		return category;
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
		
		Category category = getCategoryFromCatalog(productName);
		
		return new Item(productName, quantity, netPrice, imported, category);
	}
	
	private static boolean itemImported(String productName) {
		return productName.contains("imported");
	}
	
	private static String clearImportedProductName(String productName) {
		productName = productName.replace("imported", "");
        productName = productName.replace("  ", " ");
        return productName.trim();
	}
	
	private static Category getCategoryFromCatalog(String productName) {
		return new Catalog().getCategory(productName);
	}
 

	
}
