import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;

import java.util.*;
/*
*					       *
* Program for part of Point-Of-Sales system    *
* Program developed by Connel Evalsam Asikong  *
* Program done to handle transactions for a    *
* local hardware store and need to be able to: *
* 1. Display a menu for the user after login.  *
* 2. Validate user input .		       *
* 3. Keep track of a user's account 	       *
* (Starting balance $50.00). 		       *
* 4. Keep track of a user's purchases.	       *
* 5. Continue until the user chooses to exit.  *
*											   *
*/
public class PointOfSales{
	private static String username = "admin";
	private static String password = "password";
	
	public static void main(String[] args) {
		//declare the variables
		double balance = 0.0;
		double startingBalance = 50.00;
		Collection<Double> currentPurchasePricePer = new ArrayList();
		Collection<Double> currentPurchaseTotal = new ArrayList();
		Collection<Integer> currentPurchaseQuantity = new ArrayList();
		Collection<String> currentPurchaseItemName = new ArrayList();
		final int INITIAL_CAPACITY = 9; //capacity of the array
		Scanner input = new Scanner(System.in);
		int getInput;
		String getUsername, getPassword;
			mainLabel: //main label
				do {
					System.out.println("Welcome\n\tPress...\n1. Login\n2. Quit");
					getInput = input.nextInt();
					if (getInput == 1) {
						//main loop
						System.out.println("Welcome\n Enter username: ");
						getUsername = input.next();
						System.out.println("\nEnter password: ");
						getPassword = input.next();
						//validate username and password
						if (getUsername.equals(username) && getPassword.equals(password)) {
							//logged in
							menuLabel: //menu label
								do {
									
									System.out.println("Welcome\n1. Purchase Item\t2. Display current purchases\n3. Display account balance\t4. Complete transaction and exit.");
									getInput = input.nextInt();
									//menu control switch
									switch(getInput){
										case 1:
										//display items and prices and make purchases
											boolean go = true;
											while(go){
												HashMap items = new HashMap();
												items.put("Hammer", new Double(3.25));
												items.put("Nails", new Double(5.25));
												items.put("Paint", new Double(4.75));
												items.put("Brush", new Double(2.25));
												//to get unique values
												Set set = items.entrySet();
												//to loop through collections
												Iterator iterate = set.iterator();
												//loop through to display values
												System.out.println("| Items  |  Prices($)");
												while(iterate.hasNext()){
													Map.Entry mapEntry = (Map.Entry)iterate.next();
													System.out.println("| "+mapEntry.getKey() +" | $"+ mapEntry.getValue()+" |");
												}
												System.out.println("Type name of item to purchase:::Type done to exit");
												String getItemInput = input.next();
												getItemInput = getItemInput.toLowerCase();
												getItemInput = getItemInput.substring(0, 1).toUpperCase() + getItemInput.substring(1).toLowerCase();
												if (getItemInput.equals("done")) {
													go = false;
												}
												else {
													//check if the items exist
													if (items.containsKey(getItemInput)) {
														System.out.println("Enter Quantity");
														int getsInput = input.nextInt();
														//display balance and request for final auth
														System.out.println("Starting Balance: $"+startingBalance);
														//get quantity
														  Double quantity = ((Double)items.get(getItemInput)).doubleValue();
														  //get balance
													      balance = getsInput * quantity;
													      //sets new starting balance
													      startingBalance-= balance;
													      System.out.println("Cost of "+getsInput+" "+ getItemInput +" : $"+ balance +" balance: $"+ startingBalance);
													      if (startingBalance <= 0.00) { //check if quantity price is more than balance
													      	System.out.println("WARNING: YOU HAVE INSUFFICIENT FUNDS FOR\nTHIS PURCHASE. YOU CANNOT PURCHASE"+ getInput +" "+ getItemInput);
													      	System.out.println("You will be returned to the main menu.");
													      	//resets accounts and return to main menu
													      	startingBalance = 50.00;
													      	balance = 0.0;
													      	getInput = 0;
													      	getItemInput = "";
													      	currentPurchaseItemName.clear();
													      	currentPurchaseQuantity.clear();
													      	currentPurchasePricePer.clear();
													      	break menuLabel;
													      }
													      else { //continues with the purchase
													      	System.out.println("Do You wish to continue with purchase? Y/N");
															String choice = input.next(); //get input and check if y/n or invalid
															choice.charAt(0); //checks for the first character inputted
															choice.substring(0, 1); //converts the character back to string 
															if (choice.equalsIgnoreCase("y")) {
															//storing all purchases made to collection class
																currentPurchaseItemName.add(getItemInput);
																currentPurchaseQuantity.add(getsInput);
																currentPurchasePricePer.add(quantity);
																currentPurchaseTotal.add(balance);
																go = true;
															}
															else if (choice.equalsIgnoreCase("n")) {
																//does not save the current purchase, lets you chose another item or quantity
														      	startingBalance += balance;
																go = false;
															}
															else break;
													      }
													}
													else {
														System.out.println("Not correct");
														break;
													}
												}
											}
										
											continue menuLabel;
										case 2: //display current purchases
											System.out.println("Display current purchases:\n");
											System.out.println("Item name | Quantity | Price Per $ |");
											for (int i = 0; i < currentPurchaseItemName.size(); i++ ) {
												System.out.println("| "+currentPurchaseItemName +" | "+ currentPurchaseQuantity +" | "+ currentPurchasePricePer);
											}
											break;
										case 3: //display the balance
											System.out.println("Account Balance: "+startingBalance);
											break;
										case 4: //display final receipt and end the program
											System.out.println("Display current purchases:\n");
											System.out.println("Item name:   "+currentPurchaseItemName +"\nQuantity: "+ currentPurchaseQuantity +"\nPrice Per: "+ currentPurchasePricePer +"\nTotalPrice: "+ currentPurchaseTotal);

											System.exit(0);
										default:
											System.out.println("Invalid\nTry again");
											break;
									}
								}
								while(true);
						}
						else continue mainLabel;
					}
					else if (getInput == 2) {
						System.out.println("Bye...");
						System.exit(0);
					}
					else System.out.println("Invalid");
				}
				while (getInput !=2);
	}
}
