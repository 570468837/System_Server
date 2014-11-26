package DataService.PurchaseDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;
import ResultMessage.ResultMessage;

public interface PurchaseDataService {

	public ResultMessage addReceipt(PurchaseReceiptPO po)throws RemoteException;
	public ResultMessage deleteReceipt(PurchaseReceiptPO po)throws RemoteException;
	public ResultMessage updateReceipt(PurchaseReceiptPO po)throws RemoteException;
	public ArrayList<PurchaseReceiptPO> findReceipt(String serialNumber,String key)throws RemoteException;

}
