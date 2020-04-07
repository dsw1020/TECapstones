package com.techelevator.change;

import static org.junit.Assert.assertEquals;



import org.junit.Test;


public class TestDime {

	@Test
	public void test_dime_get_name_returns_Dime() {
		assertEquals ("Dime did not return the name Dime", "Dime", new Dime().getName());
	}
	@Test
	public void test_dime_get_value_returns_10() {
			assertEquals ("Dime did not return the value 10", 10, new Dime().getValue());
		
	}

}
