package bankingsystem;

import java.util.Map;

public class CustomerDetails {
	
	public static void customerDetails() {
		for (Map.Entry<Integer, BankCustomer> e : Login.customers.entrySet()) {
			if(e.getValue().getBal()<1000) {//removes account if user has balance less than 1000
				BankCustomer.deleteCustomer(e.getValue().getAccno());
			}
		}
		
		if(Login.customers.size()!=0) {
			System.out.println("Customer Details are : ");
			//prints details of all the customers
			for (Map.Entry<Integer, BankCustomer> e : Login.customers.entrySet()) {
				System.out.println("[ Account number : "+e.getValue().getAccno() +", Name : "+e.getValue().getCname()+", Account type : "+e.getValue().getAccType()+", Balance : "+e.getValue().getBal()+" ]");
			}
		}
		else {
			System.out.println("No customers details found");//prints if no customers are available
		}
	}
}
