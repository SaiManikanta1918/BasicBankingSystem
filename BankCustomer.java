package bankingsystem;

import java.util.Scanner;

public class BankCustomer {
	private int accno;
	private String accType, cname;
	private double bal;
	
	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public double getBal() {
		return bal;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}
	
	public static void createCustomer() {//creates customer 
		Scanner sc = new Scanner(System.in);
		int accno;
		String name,accType;
		String[] accTypes = {"Fixed Deposit","Current","Savings","Salary"};
		double bal;
		
		System.out.print("Enter Account number :");
		accno = sc.nextInt();
		System.out.print("Enter name :");
		name = sc.next();
		System.out.print("1. Fixed Deposit \n2. Current \n3. Savings \n4. Salary \nChoose Account Type :");
		int accTypeOption = sc.nextInt();
		accType = accTypes[accTypeOption-1];
		System.out.println("Enter Balance :");
		bal = sc.nextDouble();
		
		if(Login.customers.containsKey(accno)) {
			
			System.out.println("Account number already exist, please choose a different number...");
		}
		else {
			BankCustomer customer = new BankCustomer();
			
			customer.setAccno(accno);
			customer.setAccType(accType);
			customer.setBal(bal);
			customer.setCname(name);
			
			Login.customers.put(accno, customer);//adds customer into 
			
			System.out.println("Congratulations, Account created successfully...");
			System.out.println("Entered details are :[ Accno : "+customer.getAccno()+", Name : "+customer.getCname()+", Account Type : "+customer.getAccType()+", Balance : "+customer.getBal()+" ]");
		}
	}
	
	public static void deleteCustomer(int accno) {
		
		if(Login.customers.containsKey(accno)) {
			Login.customers.remove(accno);//removes account from hashmap
			System.out.println(" Account deleted successfully...");
		}
		else {
			System.out.println("User does not exist ");
		}
	}
}
