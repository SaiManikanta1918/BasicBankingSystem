package bankingsystem;

import java.util.Scanner;

class InvalidWithdraw extends Exception{//generates custom message if invalid withdrawl attempts
	InvalidWithdraw(String msg){
		super(msg);
	}
}
class InvalidChoice extends Exception{//generates custom message if invalid choice chooses
	InvalidChoice(String msg){
		super(msg);
	}
}
class InvalidDeposit extends Exception{//generates custom message if invalid deposit attempted
	InvalidDeposit(String msg){
		super(msg);
	}
}
class InvalidDetails extends Exception{//generates custom message if invalid details to be deleted
	InvalidDetails(String msg){
		super(msg);
	}
}
public class Transaction {
	int accno;
	String ttype;
	double tamt;
	Scanner sc ;
	static int transactionAttempts = 0;
	public void repeatTransaction() {
		
		System.out.println("Do you want to perform transaction ? 1/0");
		int transactionOption = sc.nextInt();
		if(transactionOption == 1)
			this.transaction();
		else {
			System.out.println("Thank You, Have a great Day...");
			System.exit(0);
		}
	}
	public void transaction() {
		sc = new Scanner(System.in);
		
		System.out.println("Bank Transaction Page...");
		
		System.out.print("Enter accno : ");
		accno = sc.nextInt();
		System.out.print("\n1. Fixed Deposit \n2. Current \n3. Savings \n4. Salary \nChoose transaction type : ");
		ttype = sc.next();
		
		//checks whether accno exists/not and acctype matches with the acctype given during account creation
		if(Login.customers.containsKey(accno) && ttype.equals(Login.customers.get(accno).getAccType())) {//

			System.out.println("1. [Withdraw]   2. [Deposit] ");
			int option = sc.nextInt();
			if(option == 1) {
				System.out.println("Enter transaction amount...");
				double tamt = sc.nextDouble();
				double customerBal = Login.customers.get(accno).getBal();
				if(tamt<customerBal) {
					//transaction amount must be >0 and should be multiples of 100
					if(tamt>0 && tamt%100==0) {
						Login.customers.get(accno).setBal(customerBal-tamt);
						System.out.println("Withdrawl Successful..");
					}
					else {
						try {
							throw new InvalidWithdraw("Sorry, Transaction amount must be multiples of 100 only...");
						}
						catch(InvalidWithdraw iw) {
							System.out.println(iw.getMessage());
						}
					}
				}
				else if(tamt>customerBal || tamt<100) {
					try {
						throw new InvalidWithdraw("Sorry,Invalid withdrawl attempted.Transaction failed ");
					}
					catch(InvalidWithdraw iw) {
						System.out.println(iw.getMessage());
					}
				}
			}
			else if( option == 2 ){
				transactionAttempts++;
				double depositAmount = 0;
				System.out.print("Enter the amount you want to deposit : ");
				depositAmount = sc.nextDouble();
				if(depositAmount>50000) {
					try {
						throw new InvalidDeposit("Invalid Deposit...");
					}
					catch(InvalidDeposit id) {
						System.out.println(id.getMessage());
					}
				}
				else {
					double customerBal = Login.customers.get(accno).getBal();
					Login.customers.get(accno).setBal(customerBal+depositAmount);
					System.out.println("Deposit Successful...");
				}
				
			}
			else {
				try {
					throw new InvalidChoice("Invalid Choice...");
				}
				catch(InvalidChoice ic) {
					System.out.println(ic.getMessage());
				}
			}
		}
		else {
			try {
				throw new InvalidDetails("User does not exist with the above details....");
			}
			catch(InvalidDetails id) {
				System.out.println(id.getMessage());
			}
		}
		Login.options();
	}
}
