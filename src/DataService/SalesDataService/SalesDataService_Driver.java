package DataService.SalesDataService;

import java.rmi.RemoteException;

import PO.SalesReceiptPO;

public class SalesDataService_Driver {
	
	public SalesDataService_Driver(SalesDataService salesController){
		try {
			salesController.addReceipt(new SalesReceiptPO());
			salesController.deleteReceipt(new SalesReceiptPO());
			salesController.findReceipt("0001","");
			salesController.updateReceipt(new SalesReceiptPO());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String [] args){
		SalesDataService salesController=new SalesController();
		new SalesDataService_Driver(salesController);
	}

}
