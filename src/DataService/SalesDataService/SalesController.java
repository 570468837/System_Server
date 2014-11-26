package DataService.SalesDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

public class SalesController implements SalesDataService{

	@Override
	public ResultMessage addReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add successful");
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage deleteReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete successful");
		return ResultMessage.delete_success;
	}

	@Override
	public ResultMessage updateReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update successful");
		return ResultMessage.update_success;
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
