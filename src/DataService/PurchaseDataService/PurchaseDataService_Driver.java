package DataService.PurchaseDataService;

import java.rmi.RemoteException;

import PO.PurchaseReceiptPO;

public class PurchaseDataService_Driver {
	public PurchaseDataService_Driver(PurchaseDataService purchaseController){
		try {
			purchaseController.addReceipt(new PurchaseReceiptPO(null, "JHD-YYMMDD-00001",null, "12-11", "", 1000));
			System.out.println("添加成功");
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
