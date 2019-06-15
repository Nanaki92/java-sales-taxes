package it.nanaki92.salestaxes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CashRegisterTest {
	
	private static final int STANDARD_TAX = 10;
	
	@Test
	public void sellOneMiscItemNoTaxNoImported() throws Exception {
		
		CashRegister cashRegister = new CashRegister(STANDARD_TAX);
		cashRegister.checkoutItem("1 book at 12.49");
		
		String recipit = cashRegister.printRecipit();
		
		String expected = "1 book: 12.49\n" +
				"Sales Taxes: 0.00\n" + 
				"Total: 12.49";
		assertEquals(expected, recipit);
	}
	
	@Test
	public void sellOneMiscItemWithTaxNoImported() throws Exception {
		
		CashRegister cashRegister = new CashRegister(STANDARD_TAX);
		cashRegister.checkoutItem("1 music CD at 14.99");
		
		String recipit = cashRegister.printRecipit();
		
		String expected = "1 music CD: 16.49\n" +
				"Sales Taxes: 1.50\n" + 
				"Total: 16.49";
		assertEquals(expected, recipit);
	}
	
	
}
