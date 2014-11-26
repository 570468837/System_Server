package DataService.SalesDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

public interface SalesDataService {

	public ResultMessage addReceipt(SalesReceiptPO po)throws RemoteException;
	public ResultMessage deleteReceipt(SalesReceiptPO po)throws RemoteException;
	public ResultMessage updateReceipt(SalesReceiptPO po)throws RemoteException;
	public ArrayList<SalesReceiptPO> findReceipt(String serialNumber,String key)throws RemoteException;
}
