package it.nanaki92.salestaxes.recipit;

import java.math.BigDecimal;
import java.util.ArrayList;

import it.nanaki92.salestaxes.billing.Tax;
import it.nanaki92.salestaxes.warehouse.Item;

public class Recipit {

	private Tax tax;
	private ArrayList<Item> selledItems;
	private BigDecimal salesTaxes;
	private BigDecimal total;
	
	public Recipit(Tax tax) {
		this.tax = tax;
        this.selledItems = new ArrayList<Item>();
        this.salesTaxes = BigDecimal.ZERO;
		this.total = BigDecimal.ZERO;
	}
	
	public void add(Item item) {

		BigDecimal taxOnItem = tax.calculateTaxOnItem(item);
		BigDecimal grossPrice = calculateGrossPrice(item);
		
		selledItems.add(item);		
		salesTaxes = salesTaxes.add(taxOnItem);
		total = total.add(grossPrice);
	}
	
	private BigDecimal calculateGrossPrice(Item item) {	
		BigDecimal taxOnItem = tax.calculateTaxOnItem(item);
		BigDecimal grossPrice = item.getNetPrice().add(taxOnItem);

		//grossPrice = grossPrice.setScale(2, RoundingMode.HALF_UP);
		
		return grossPrice;
	}
	
	public String printRecipit() {
		String recipit = "";
		
		for (Item item : selledItems) {
			 recipit += printItemOnRecipit(item);
		}
		
		recipit += "Sales Taxes: " + salesTaxes + "\n";
		recipit += "Total: " + total;
		
		return recipit;
	}
	
	private String printItemOnRecipit(Item item) {
		String recipit = item.getQuantity() + " " + importedPrint(item) + item.getProductName() + ": " + calculateGrossPrice(item) + "\n";
		return recipit;		
	}
	
	private String importedPrint(Item item) {
		if (item.isImported()) {
			return "imported ";
		}
		return "";
	}
	
}
