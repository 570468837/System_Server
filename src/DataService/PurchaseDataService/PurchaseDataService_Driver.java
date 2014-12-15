package DataService.PurchaseDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Config.Level;
import Config.Sort;
import Config.UserSort;
import PO.CustomerPO;
import PO.GoodsPO;
import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import PO.UserPO;

public class PurchaseDataService_Driver {
	public PurchaseDataService_Driver(PurchaseDataService purchaseController){
		try {
			CustomerPO customer=new CustomerPO("001", Sort.importer,Level.firstClass, "gaoyang", "12345","hunan", "413000","@", "hutao");
			UserPO po=new UserPO("gaoyang","123",UserSort.Admin, 0);
			purchaseController.addReceipt(new PurchaseReceiptPO(customer, "JHD-YYMMDD-00001",po, "2014-12-11", "", 1000));
			ArrayList<PurchaseListItemPO> list=new ArrayList<PurchaseListItemPO>();
			GoodsPO goods=new GoodsPO("009","hutao", "DF", 100, "HEHE");
			
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
