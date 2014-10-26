package DataService.CustomerDataService;

import java.rmi.RemoteException;

import PO.CustomerPO;


public class CustomerDataService_Driver {
	public CustomerDataService_Driver(CustomerDataService cds){
		try {
			cds.insertCustomer(new CustomerPO());
			cds.deleteCustomer(new CustomerPO());
			cds.updateCustomer(new CustomerPO());
			cds.findCustomer("",0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	public static void main(String [] args){
		CustomerDataService customerController=new CustomerController();
		new CustomerDataService_Driver(customerController);
	}
	
}
