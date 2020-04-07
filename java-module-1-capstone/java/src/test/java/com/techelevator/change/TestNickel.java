package com.techelevator.change;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNickel {

	@Test
	public void test_nickel_get_name_returns_Nickle() {
		assertEquals ("Nickel did not return the name Nickel", "Nickel", new Nickel().getName());
	}
	@Test
	public void test_nickel_get_value_returns_10() {
			assertEquals ("Nickel did not return the value 5", 5, new Nickel().getValue());
		
	}

}