package DataService.PurchaseDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class PurchaseController implements PurchaseDataService{

ArrayList<PurchaseReceiptPO> purchaseReceipts=new ArrayList<PurchaseReceiptPO>(); 
	
	public PurchaseController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas\\PurchaseReceiptPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			purchaseReceipts=(ArrayList<PurchaseReceiptPO>)oin.readObject();
			}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}          
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			FileOutputStream fos;
			ObjectOutputStream oos;
			fos = new FileOutputStream("Datas\\PurchaseReceiptPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(purchaseReceipts);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	@Override
	public ResultMessage addReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
				boolean exist=false;
				for(PurchaseReceiptPO p:purchaseReceipts){
					if(p.getSerialNumber().equals(p.getSerialNumber())){
						exist=true;
						break;
						}
					}
				if(!exist){
					purchaseReceipts.add(po);
					save();
					return ResultMessage.add_success;
				}
				else
					return ResultMessage.create_purchasereceipt_failure;
	
	}

	
	
	@Override
	public ResultMessage deleteReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete successful");
		return ResultMessage.create_purchasereceipt_failure;
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
