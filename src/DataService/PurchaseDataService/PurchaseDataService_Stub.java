package DataService.PurchaseDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;
import ResultMessage.ResultMessage;

public class PurchaseDataService_Stub implements PurchaseDataService{

	@Override
	public ResultMessage addReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add successful");
		return null;
	}

	@Override
	public ResultMessage deleteReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete successful");
		return null;
	}

	@Override
	public ResultMessage updateReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update successful");
		return null;
		
	}

	@Override
	public ArrayList<PurchaseReceiptPO> findReceipt(String serialNumber,
			String key) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PurchaseReceiptPO> polist=new ArrayList<PurchaseReceiptPO>();
		polist.add(new PurchaseReceiptPO());
		System.out.println("find successful");
		return polist;
	}

}
