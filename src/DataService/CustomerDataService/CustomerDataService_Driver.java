package DataService.CustomerDataService;

import java.rmi.RemoteException;

import Config.Level;
import Config.Sort;
import PO.CustomerPO;


public class CustomerDataService_Driver {
	public CustomerDataService_Driver(CustomerDataService cds){
		try {
			cds.insertCustomer(new CustomerPO("009", Sort.importer,Level.firstClass, "gaoyang", "1235","NJU", "123","333", "xiaoyu"));
			cds.insertCustomer(new CustomerPO("001", Sort.importer,Level.firstClass, "hutao", "1235","NJU", "123","333", "taohu"));
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
