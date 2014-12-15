package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import DataService.CommodityDataService.CommodityController;
import DataService.CustomerDataService.CustomerController;
import DataService.FinanceDataService.FinanceController;
import DataService.GoodsDataService.GoodsController;
import DataService.InfoDataService.InfoController;
import DataService.PromotionDataService.PromotionController;
import DataService.PurchaseDataService.PurchaseController;
import DataService.SalesDataService.SalesController;
import DataService.UserDataService.UserController;
import PO.*;
import ResultMessage.ResultMessage;


public class Communication_Controller extends UnicastRemoteObject implements Communication{
	public GoodsController goodsController;
	public CommodityController commodityController;
	protected Communication_Controller() throws RemoteException {
		super();
		goodsController = new GoodsController();
		commodityController = new CommodityController(this);
		
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
		if(command.equals("collectionOrPaymentAdd")){
			return new FinanceController().insertCollectionOrPaymentPO((CollectionOrPaymentPO)PO) ;
		}
		if(command.equals("collectionOrPaymentUpdate")){
			return new FinanceController().updateCollectionOrPayment((CollectionOrPaymentPO)PO) ;
		}
		if(command.equals("cashAdd")){
			return new FinanceController().insertCash((CashPO)PO);
		}

		if(command.equals("addCustomer")){
			return new CustomerController().insertCustomer((CustomerPO)PO);
		}
		if(command.equals("deleteCustomer")){
			return new CustomerController().deleteCustomer((CustomerPO)PO);
		}
		if(command.equals("updateCustomer")){
			return new CustomerController().updateCustomer((CustomerPO)PO);
		}if(command.equals("collection_payment_change_pay_getting")){
			return new CustomerController().collectionOrPaymentChangePayOrGetting((CollectionOrPaymentPO)PO);
		}if(command.equals("purchase_change_pay")){
			return new CustomerController().purchaseChangePay((PurchaseReceiptPO)PO);
		}if(command.equals("sales_change_getting")){
			return new CustomerController().salesChangeGetting((SalesReceiptPO)PO);
		}
		
		if(command.equals("creat_purchase_receipt")){
			 return new PurchaseController().addReceipt((PurchaseReceiptPO)PO);
		}
		if(command.equals("creat_sales_receipt")){
			return new SalesController().addReceipt((SalesReceiptPO)PO);
		}
		if(command.equals("goodsAdd")) {
			return goodsController.addGoods((GoodsPO)PO);
		}
		if(command.equals("goodsDel")) {
			return goodsController.delGoods(Long.parseLong(((GoodsPO)PO).getSerialNumber()));
		}
		if(command.equals("goodsUpd")) {
			return goodsController.updGoods((GoodsPO)PO);
		}
		if(command.equals("goodsClassAdd")) {
			return goodsController.addGoodsClass((GoodsClassPO)PO);
		}
		if(command.equals("goodsClassDel")) {
			return goodsController.delGoodsClass(((GoodsClassPO)PO).Num);
		}
		if(command.equals("goodsClassUpd")) {
			return goodsController.updGoodsClass((GoodsClassPO)PO);
		}
		if(command.equals("updUncheckedSend")) {
			return commodityController.updUncheckedSend((ArrayList<SendCommodityPO>)PO);
		}
		if(command.equals("commodityReport")) {
			System.out.println("aaaaa");
			return commodityController.insertReportCommodity((ReportCommodityPO)PO);
		}
		if(command.equals("commoditySend")) {
			return commodityController.insertSendCommodity((SendCommodityPO)PO);
		}
		
		
		
		
		return null;
	}


	public ArrayList<Object> findObject(String command, String keywords)
			throws RemoteException {
		if(command.equals("userFind")){
			return new UserController().find(keywords);
		}
		if(command.equals("accountFind")){
			return new FinanceController().find(keywords) ;
		}
		else{
			return null;
		}
	} 
	

	public ArrayList<Object> showObject(String command)
			throws RemoteException {
		if(command.equals("userShow")){
			return new UserController().show();
		}
		if(command.equals("promotionShow")){
			return new PromotionController().show();
		}
		if(command.equals("accountShow")){
			return new FinanceController().show() ;
		}
		if(command.equals("collectionOrPaymentShow")){
			return new FinanceController().showCollectionOrPayment() ;
		}
		if(command.equals("cashShow")){
			return new FinanceController().showCash() ;
		}
		if(command.equals("showCustomer")){
			return new CustomerController().show();
		}
		if(command.equals("showPurchaseReceipts")){
			return new PurchaseController().show();
		}
		if(command.equals("showSalesReceipts")){
			return new SalesController().show();
		}
		if(command.equals("goodsListGet")) {
			return goodsController.getGoodsPOList();
		}
		if(command.equals("goodsClassListGet")) {
			return goodsController.getGoodsClassPOList();
		}
		if(command.equals("uncheckedSendShow")) {
			return commodityController.showUncheckedSend();
		}
		if(command.equals("inventoryCommodity")) {
			return commodityController.getInventoryCommodity().icInfo;
		}
		if(command.equals("showSendCommodity")) {
			return commodityController.showSendCommodity();
		}
		if(command.equals("showReportCommodity")) {
			return commodityController.showReportCommodity();
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


	@Override
	public String getReceiptNumber(String typeOfReceipt) throws RemoteException {
		// TODO Auto-generated method stub
		if(typeOfReceipt.equals("SKD")||typeOfReceipt.equals("FKD")){
			return new FinanceController().getReceiptNumber(typeOfReceipt) ;
		}
		if(typeOfReceipt.equals("XJFYD")){
			return new FinanceController().getReceiptNumber(typeOfReceipt);
		}
		else{
	    	return "错误";
		}
	}
	
	public Object someMethodForFinancer(String command,String time1,String time2){
		if(command.equals("showIncomeInATime")){
			return new SalesController().getTotalIncomeInATime(time1, time2);
		}
		if(command.equals("showCostInATime")){
			return new SalesController().getTotalCostInATime(time1, time2);
		}
		if(command.equals("showDiscountInATime")){
			return new SalesController().getTotalDiscountInAtime(time1, time2);
		}
		if(command.equals("showDifferenceInATime")){
			return new SalesController().getDiffrence(time1, time2);
		}
		if(command.equals("showDifferenceFromVocherInATime")){
			return new SalesController().getDifferenceFromVocherInATime(time1, time2);
		}if(command.equals("showAllPurchaseReceiptsInATime")){
			return new PurchaseController().getReceiptsInATime(time1, time2);
		}
		if(command.equals("showAllSalesReceiptsInATime")){
			return new SalesController().getReceiptsInATime(time1, time2);
		}
		if(command.equals("reportIncome")){
			return commodityController.reportIncome(time1, time2);
		}
		if(command.equals("reportOutcome")){
			return commodityController.reportOutcome(time1, time2);
		}
		if(command.equals("sendOutcome")){
			return commodityController.sendOutcome(time1, time2);
		}
		
		else{
			return null;
		}
	}
	
	
	
}
