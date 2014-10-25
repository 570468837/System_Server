package DataService.SalesDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;

public class SalesDataSerivce_Stub implements SalesDataServie {

	@Override
	public void addReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add successful");
	}

	@Override
	public void deleteReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete successful");
	}

	@Override
	public void updateReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update successful");
	}

	@Override
	public ArrayList<SalesReceiptPO> findReceipt(String serialNumber, String key)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> polist=new ArrayList<SalesReceiptPO>();
		polist.add(new SalesReceiptPO());
		System.out.println("find successful");
		return polist;
	}

}
