package DataService.SalesDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.SalesReceiptPO;

public interface SalesDataService {

	public void addReceipt(SalesReceiptPO po)throws RemoteException;
	public void deleteReceipt(SalesReceiptPO po)throws RemoteException;
	public void updateReceipt(SalesReceiptPO po)throws RemoteException;
	public ArrayList<SalesReceiptPO> findReceipt(String serialNumber,String key)throws RemoteException;
}
