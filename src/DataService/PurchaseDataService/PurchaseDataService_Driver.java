package DataService.PurchaseDataService;

import java.rmi.RemoteException;

import PO.PurchaseReceiptPO;

public class PurchaseDataService_Driver {
	public PurchaseDataService_Driver(PurchaseDataService purchaseController){
		try {
			purchaseController.addReceipt(new PurchaseReceiptPO());
			purchaseController.deleteReceipt(new PurchaseReceiptPO());
			purchaseController.findReceipt("001","");
			purchaseController.updateReceipt(new PurchaseReceiptPO());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String []args){
		PurchaseDataService purchaseController=new PurchaseController();
		new PurchaseDataService_Driver(purchaseController);		
	}

}
