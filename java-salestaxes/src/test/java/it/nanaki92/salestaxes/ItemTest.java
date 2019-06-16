package it.nanaki92.salestaxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import it.nanaki92.salestaxes.warehouse.Item;

public class ItemTest {
	
	@Test
	public void parseOneItemWithPrice() throws Exception {
		Item item = Item.parseOrderToItem("1 imported box of chocolates at 10.00");
		
		assertEquals("1", item.getQuantity());
		assertEquals("box of chocolates", item.getProductName());
		assertEquals(new BigDecimal("10.00"), item.getNetPrice());
		
	}
	
}
