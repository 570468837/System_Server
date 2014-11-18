package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import DataService.FinanceDataService.FinanceController;
import DataService.PromotionDataService.PromotionController;
import DataService.UserDataService.UserController;
import PO.AccountPO;
import PO.PromotionPO;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class Communication_Controller extends UnicastRemoteObject implements Communication{
	protected Communication_Controller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void voidCommand(String command,Object PO) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	@Override
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
		if(command.equals("PromotionAddPackage")){
			return new PromotionController().addPackage((PromotionPO)PO);
		}
		if(command.equals("PromotionAddGift")){
			return new PromotionController().addGift((PromotionPO)PO);
		}
		if(command.equals("PromotionAddVoucher")){
			return new PromotionController().addVoucher((PromotionPO)PO);
		}
		if(command.equals("PromotionDelete")){
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
			return null;
		}

	@Override
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
	
	@Override
	public ArrayList<Object> showObject(String command)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	}
