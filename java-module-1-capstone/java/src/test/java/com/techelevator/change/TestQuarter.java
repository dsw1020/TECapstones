package com.techelevator.change;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestQuarter {

	@Test
	public void test_quarter_get_name_returns_Quarter() {
		assertEquals ("Quarter did not return the name Quarter", "Quarter", new Quarter().getName());
	}
	@Test
	public void test_quarter_get_value_returns_25() {
			assertEquals ("Quarter did not return the value 25", 25, new Quarter().getValue());
		
	}

}

	