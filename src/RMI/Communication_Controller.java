package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import DataService.CustomerDataService.CustomerController;
import DataService.FinanceDataService.FinanceController;
import DataService.PromotionDataService.PromotionController;
import DataService.PurchaseDataService.PurchaseController;
import DataService.SalesDataService.SalesController;
import DataService.UserDataService.UserController;
import PO.AccountPO;
import PO.CustomerPO;
import PO.PromotionPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import ResultMessage.ResultMessage;


public class Communication_Controller extends UnicastRemoteObject implements Communication{
	protected Communication_Controller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	public void voidCommand(String command,Object PO) throws RemoteException{
		// TODO Auto-generated method stub 
		
	}

	public ResultMessage messageCommand(String command, Object PO) throws RemoteException{
		
		if(command.equals("userLogin")){
			return new UserController().check((UserPO)PO);
		}
		if(command.equals("userAdd")){
			return new UserController().add((UserPO)PO);
		}
		if(command.equals("userDelete")){
			return new UserController().delete((UserPO)PO);
		}
		if(command.equals("userUpdate")){
			return new UserController().update((UserPO)PO);
		}
		if(command.equals("promotionAddPackage")){
			return new PromotionController().addPackage((PromotionPO)PO);
		}
		if(command.equals("promotionAddGift")){
			return new PromotionController().addGift((PromotionPO)PO);
		}
		if(command.equals("promotionAddVoucher")){
			return new PromotionController().addVoucher((PromotionPO)PO);
		}
		if(command.equals("promotionDelete")){
			return new PromotionController().delete((PromotionPO)PO);
		}
		if(command.equals("accountAdd")){
			return new FinanceController().insertAccount((AccountPO)PO) ;
		}
		if(command.equals("accountDelete")){
			return new FinanceController().delet((AccountPO)PO);
		}
		if(command.equals("accountUpdate")){
			return new FinanceController().update((AccountPO)PO);
		}
		if(command.equals("addCustomer")){
			return new CustomerController().insertCustomer((CustomerPO)PO);
		}
		if(command.equals("deleteCustomer")){
			return new CustomerController().deleteCustomer((CustomerPO)PO);
		}
		if(command.equals("updateCustomer")){
			return new CustomerController().updateCustomer((CustomerPO)PO);
		}
		if(command.equals("creat_purchase_receipt")){
			 return new PurchaseController().addReceipt((PurchaseReceiptPO)PO);
		}if(command.equals("creat_sales_receipt")){
			return new SalesController().addReceipt((SalesReceiptPO)PO);
		}
			return null;
		}


	public ArrayList<Object> findObject(String command, String keywords)
			throws RemoteException {
		// TODO Auto-generated method stub
		if(command.equals("userFind")){
			return new UserController().find(keywords);
		}
		else{
		return null;
		}
	} 
	

	public ArrayList<Object> showObject(String command)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	}
