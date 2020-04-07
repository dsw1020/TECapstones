package com.techelevator;

		public class Dollar {
			
			public static final Dollar ZERO_DOLLARS = new Dollar(0);
	
			/************************************************************************************************
			 * Attributes
			 ***********************************************************************************************/		
			
			
			private int totalCents;
			
			/************************************************************************************************
			 * 1 arg Constructor
			 ***********************************************************************************************/
			
			public Dollar(int totalCents) {
				this.totalCents = totalCents;
			}
			
		
			/************************************************************************************************
			 * Getters
			 ***********************************************************************************************/
			
			public int getCents() {
				return (int) (totalCents % 100);
			}
			
			public int getDollars() {
				return totalCents / 100;
			}
			
			public int getTotalCents() {
				return totalCents;
			}
			
			
			/************************************************************************************************
			 * Methods
			 ***********************************************************************************************/
			
			public boolean isGreaterThan(Dollar amountToCompare) {
				return this.totalCents > amountToCompare.totalCents;
			}
			
			public boolean isLessThan(Dollar amountToCompare) {
				return this.totalCents < amountToCompare.totalCents;
			}
			
			public boolean isNegative() {
				return totalCents < 0;
			}
			
			public Dollar subtract(Dollar amountToSubtract) {
				return new Dollar(this.totalCents - amountToSubtract.totalCents);
			}
			
			public Dollar add(Dollar amountToAdd) {
				return new Dollar(this.totalCents + amountToAdd.totalCents);
			}
			
			public int compare(Dollar amountToCompare) {
				if (this.isGreaterThan(amountToCompare))	{
					return 1;
				}
				else if (this.isLessThan(amountToCompare)) {
					return -1;
				}
				else {
					return 0;
				}
			}
			
			/************************************************************************************************
			 * @Overrides
			 ***********************************************************************************************/	
		
			
		
			@Override
			public String toString() {
				return "$" + getDollars() + "." + String.format("%02d", getCents());
			}
			
			/************************************************************************************************
			 * Return Method with System.out Functions 
			 ***********************************************************************************************/	
			
			public void returnChange(){
				int totalCents = this.getCents();
				int totalNickels = 0;
				int totalDimes = 0;
				int totalQuarters = 0;
				while (totalCents >0){
					if (totalCents >= 25){
						totalQuarters ++;
						totalCents-= 25;
					}else if (totalCents >= 10){
						totalDimes++;
						totalCents-= 10;
					}else if (totalCents >= 5){
						totalNickels++;
						totalCents-= 5;
					}
				}
				System.out.println("Change returned: " + totalQuarters + " quarters, " + totalDimes + " dimes, " + totalNickels + " nickels, " );
				System.out.println("Thank You!");
			
			}	
			
			public static boolean isValidDollar(String checkDollar) {
				return checkDollar.equals("1") || checkDollar.equals("2") || 
						checkDollar.equals("5") || checkDollar.equals("10") ; 
			}
			
			
	
		}
			
	
