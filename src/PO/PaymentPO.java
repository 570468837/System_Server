package PO;

import java.util.ArrayList;

public class PaymentPO {
	String number ;
	CustomerPO customer ;
	UserPO user ;
	ArrayList<TransferListItem> tfList =new ArrayList<TransferListItem>();//转账列表	
	double total ;
	
	public PaymentPO(){
		number = null;
		customer = null;
		user = null ;
		total =0;
	}
	
	public PaymentPO(String theNumber,CustomerPO theCustomer,UserPO theUser,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		total = theSum ;
	}
	

	public void add(TransferListItem theItem){
		this.tfList.add(theItem) ;
	}
	
	public double getTotal(){
		double total = 0 ;
		for(TransferListItem theItem : tfList){
			total += theItem.getTransferMoney() ;
		}
		return total ;
	}
}
