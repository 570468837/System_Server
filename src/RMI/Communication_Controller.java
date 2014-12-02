package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import DataService.CustomerDataService.CustomerController;
import DataService.FinanceDataService.FinanceController;
import DataService.GoodsDataService.GoodsController;
import DataService.InfoDataService.InfoController;
import DataService.PromotionDataService.PromotionController;
import DataService.PurchaseDataService.PurchaseController;
import DataService.SalesDataService.SalesController;
import DataService.UserDataService.UserController;
import PO.AccountPO;
import PO.CustomerPO;
import PO.GoodsClassPO;
import PO.PromotionPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import PO.ScreeningConditionPO;
import PO.UserPO;
import PO.GoodsPO;
import ResultMessage.ResultMessage;


public class Communication_Controller extends UnicastRemoteObject implements Communication{
	GoodsController goodsController;
	protected Communication_Controller() throws RemoteException {
		super();
		goodsController = new GoodsController();
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
		}
		if(command.equals("creat_sales_receipt")){
			return new SalesController().addReceipt((SalesReceiptPO)PO);
		}
		if(command.equals("goodsAdd")) {
			return goodsController.addGoods((GoodsPO) PO);
		}
		if(command.equals("goodsDel")) {
			return goodsController.delGoods(Long.parseLong(((GoodsPO) PO).getSerialNumber()));
		}
		if(command.equals("goodsUpd")) {
			return goodsController.updGoods((GoodsPO) PO);
		}
		if(command.equals("goodsClassAdd")) {
			return goodsController.addGoodsClass((GoodsClassPO) PO);
		}
        if(command.equals("goodsClassDel")) {
        	return goodsController.delGoodsClass(((GoodsClassPO) PO).Num);
		}
        if(command.equals("goodsClassUpd")) {
        	return goodsController.updGoodsClass((GoodsClassPO) PO);
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
		if(command.equals("userShow")){
			return new UserController().show();
		}
		if(command.equals("promotionShow")){
			return new PromotionController().show();
		}
		else{
			return null;
		}
	}

	public ArrayList<Object> showObjectByPO(String command, Object PO)
			throws RemoteException {
		// TODO Auto-generated method stub
		if(command.equals("promotionIfPackage")){
			return new PromotionController().ifPackage((SalesReceiptPO)PO);
		}
		if(command.equals("promotionIfGift")){
			return new PromotionController().ifGift((SalesReceiptPO)PO);
		}
		if(command.equals("promotionIfVoucher")){
			return new PromotionController().ifVoucher((SalesReceiptPO)PO);
		}
		return null;
	}


	public ArrayList<Object> showReceipt(String command,
			ScreeningConditionPO condition) throws RemoteException {
		// TODO Auto-generated method stub
		if(command.equals("showSalesDetailsInfo")){
			return new InfoController().findReceipt(condition) ;
		}
		if(command.equals("showSalesProcessInfo")){
			return new InfoController().findReceipt(condition) ;
		}
		
		return null;
	}


	
	public String showSalesConditionInfo(String command, String time1, String time2)
			throws RemoteException {
		// TODO Auto-generated method stub
		if(command.equals("showSalesConditionInfo")){
			return new InfoController().getSalesCondition(time1, time2) ;
		}
		return null;
	}


	public ResultMessage mangeReceipt(String command, String typeOfReceipt,
			String number) throws RemoteException {
		// TODO Auto-generated method stub
		if(command.equals("receiptDelet")){
			return new InfoController().deletReceipt(typeOfReceipt, number) ;
		}
		if(command.equals("receiptDeletAndUpdat")){
			return new InfoController().deletAndUpdateReceipt(typeOfReceipt, number) ;
		}
		return null;
	}


	
	
	}
