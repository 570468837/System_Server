package DataService.SalesDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

public class SalesController implements SalesDataService{
ArrayList<SalesReceiptPO> salesReceipts=new ArrayList<SalesReceiptPO>(); 
	
	public SalesController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas\\SalesReceiptPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			salesReceipts=(ArrayList<SalesReceiptPO>)oin.readObject();
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
			fos = new FileOutputStream("Datas\\SalesReceiptPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(salesReceipts);
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
	public ResultMessage addReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
				boolean exist=false;
				for(SalesReceiptPO p:salesReceipts){
					if(p.getSerialNumber().equals(p.getSerialNumber())){
						exist=true;
						break;
						}
					}
				if(!exist){
					salesReceipts.add(po);
					save();
					return ResultMessage.add_success;
				}
				else
					return ResultMessage.create_purchasereceipt_failure;
	
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
