package DataService.PurchaseDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;

public interface PurchaseDataService {

	public void addReceipt(PurchaseReceiptPO po)throws RemoteException;
	public void deleteReceipt(PurchaseReceiptPO po)throws RemoteException;
	public void updateReceipt(PurchaseReceiptPO po)throws RemoteException;
	public ArrayList<PurchaseReceiptPO> findReceipt(String serialNumber,String key)throws RemoteException;

}
