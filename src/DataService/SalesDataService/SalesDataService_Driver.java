package DataService.SalesDataService;

import java.rmi.RemoteException;

import PO.SalesReceiptPO;

public class SalesDataService_Driver {
	
	public SalesDataService_Driver(SalesController salesController){
		salesController.show();
		
	}
	
	public static void main(String [] args){
		SalesController salesController=new SalesController();
		new SalesDataService_Driver(salesController);
	}

}
