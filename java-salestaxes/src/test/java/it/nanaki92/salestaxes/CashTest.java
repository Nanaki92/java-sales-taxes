package it.nanaki92.salestaxes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CashTest {
	
	@Test
	public void sellOneMiscItemNoTaxNoImported() throws Exception {
		
		Cash cash = new Cash();
		cash.sellItem("1 book at 12.49");
		
		String recipit = cash.printRecipit();
		
		String expected = "1 book: 12.49\n" +
				"Sales Taxes: 0.00\n" + 
				"Total: 12.49";
		assertEquals(expected, recipit);
	}
	
	@Test
	public void sellOneMiscItemWithTaxNoImported() throws Exception {
		
		Cash cash = new Cash();
		cash.sellItem("1 music CD at 14.99");
		
		String recipit = cash.printRecipit();
		
		String expected = "1 music CD: 16.49\n" +
				"Sales Taxes: 1.50\n" + 
				"Total: 16.49";
		assertEquals(expected, recipit);
	}
	
	
}
