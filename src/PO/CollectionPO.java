package PO;

import java.util.ArrayList;

public class CollectionPO{
//�տ
	String number ;
	CustomerPO customer ;
	UserPO user ;
	ArrayList<TransferListItem> tfList = new ArrayList<TransferListItem>() ;//转账列表
	double total;
	public CollectionPO(){
		number = null ;
	    customer = null ;
	    user = null ;
	    total = 0 ;
	}
	
	public CollectionPO(String theNumber,CustomerPO theCustomer,UserPO theUser,double theSum){
		number = theNumber ;
		customer = theCustomer ;
		user = theUser ;
		total = theSum ;
	}
	
	public void add(TransferListItem theItem){
		tfList.add(theItem) ;
	}
	
	public double getTotal(){
		double total = 0 ;
		for(TransferListItem theItem : tfList){
			total += theItem.getTransferMoney();
		}
		return total ;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public CustomerPO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerPO customer) {
		this.customer = customer;
	}
	public UserPO getUser() {
		return user;
	}
	public void setUser(UserPO user) {
		this.user = user;
	}

	
}