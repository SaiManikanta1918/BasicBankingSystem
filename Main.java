package bankingsystem;

import java.util.HashMap;
import java.util.Scanner;

class InvalidUser extends Exception {//throws custom message if entered details are not correct
	
	public InvalidUser(String name){
		super(name);
	}
	
}

class Login {
	static int countAttempts = 0;
	static Scanner sc = new Scanner(System.in);
	public static HashMap<Integer, BankCustomer> customers = new HashMap<>();//it stores details of all the customers
	public static void options() {
		int optionForContinue =0;
		do {
			System.out.println("1. Transactions   2. Customer Details  3. Add Customer  4. Delete Customer");
			int option = sc.nextInt();
			switch(option) {
				case 1 : Transaction t = new Transaction();
						t.transaction();
						break;
				case 2 : CustomerDetails.customerDetails();
						break;
				case 3 : BankCustomer.createCustomer();
						break;
				case 4 : System.out.println("Enter account number you want to delete : ");
						 int accno = sc.nextInt();
						 BankCustomer.deleteCustomer(accno);
						 break;
				case 5: System.out.println("exit");
						System.exit(0);
				default : System.out.println("Invalid Choice...");
						  break;
			}
			System.out.println("Do you want to continue ? 1/0 ");
			optionForContinue = sc.nextInt();
			if(optionForContinue !=1 && optionForContinue != 0) {
				System.out.println("Invalid Choice...");
				System.exit(0);
			}
		}while(optionForContinue == 1);
	}
	
	public static void validate(String userId, String password) {
		Scanner sc = new Scanner(System.in);
		if(Main.validUsers.containsKey(userId) && Main.validUsers.get(userId).equals(password)) {
			System.out.println("Login Successful...");
			options();
		}
		else {
			try {
				throw new InvalidUser("Invalid User...");
			}
			catch(InvalidUser invaliduser) {
				countAttempts++;
				if(countAttempts == 5) {
					System.out.println("Sorry, You have reached maximum number of attempts");
					System.exit(0);
				}
				System.out.println(invaliduser.getMessage());
				System.out.println("Do you want to continue ? 1/0");
				int option = sc.nextInt();
				if(option==1) {
					String inputUserId, inputPassword;
					System.out.print("Enter userId :");
					inputUserId = sc.next();
					System.out.print("Enter password : ");
					inputPassword =sc.next();
					validate(inputUserId, inputPassword);
				}
				else {
					System.out.println("Thank you for visiting....");
					System.exit(0);//forcibly exits the program
				}
			}
		}
	}
}
public class Main {

	public static HashMap<String, String> validUsers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		
		validUsers = new HashMap<>();//Valid users given statically
		validUsers.put("sai","sai000");
		validUsers.put("SaiManikanta", "Sai999");
		validUsers.put("12345", "12345");
		
		String inputUserId, inputPassword;
		System.out.print("Enter userId :");
		inputUserId = sc.next();
		System.out.print("Enter password : ");
		inputPassword =sc.next();
		
		Login.validate(inputUserId, inputPassword);//function call that validates the entered details

	}
}