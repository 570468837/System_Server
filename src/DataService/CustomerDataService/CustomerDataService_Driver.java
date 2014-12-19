package DataService.CustomerDataService;

import java.rmi.RemoteException;

import Config.Level;
import Config.Sort;
import PO.CustomerPO;


public class CustomerDataService_Driver {
	public CustomerDataService_Driver(CustomerDataService cds){
		try {
			CustomerPO customer1=new CustomerPO("010", Sort.importer,Level.firstClass, "gaoyang2", "1235","NJU", "123","333", "xiaoyu");
			CustomerPO customer2=new CustomerPO("011", Sort.importer,Level.firstClass, "hutao2", "1235","NJU", "123","333", "taohu");
			customer1.setPay(1000);
			customer1.setGetting(1000);
			
			customer2.setPay(1000);
			customer2.setPay(1000);
			
			cds.insertCustomer(customer1);
			cds.insertCustomer(customer2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	public static void main(String [] args){
		CustomerDataService customerController=new CustomerController();
		new CustomerDataService_Driver(customerController);
//		try {
//			System.out.println(new CustomerController().findCustomer("gaoyang"));
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
}
