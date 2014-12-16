package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionOrPaymentPO implements Serializable{
	String number ;
	String customer ;
	String typeOfCustomer ;
	String user ;
	ArrayList<TransferListItemPO> trList = new ArrayList<TransferListItemPO>();//转账列表
	double total ;
	boolean isApprovedByManager  ;
	boolean isApprovedByFinancer  ;
	public CollectionOrPaymentPO(){
		number = null ;
		customer = null ;
		user = null ;
		trList  = null ;
		total = 0 ;
		isApprovedByManager = false ;
		isApprovedByFinancer = false ;
	}
	public CollectionOrPaymentPO(String number, String customer,
			String typeOfCustomer, String user,
			ArrayList<TransferListItemPO> trList, double total,
			boolean isApprovedByManager, boolean isApprovedByFinancer) {
		super();
		this.number = number;
		this.customer = customer;
		this.typeOfCustomer = typeOfCustomer;
		this.user = user;
		this.trList = trList;
		this.total = total;
		this.isApprovedByManager = isApprovedByManager;
		this.isApprovedByFinancer = isApprovedByFinancer;
	}
	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}
	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}
	public ArrayList<TransferListItemPO> getTrList() {
		return trList;
	}
	public void setTrList(ArrayList<TransferListItemPO> trList) {
		this.trList = trList;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void add(TransferListItemPO theItem){
		trList.add(theItem) ;
	}
	public double getTotal(){
		double total = 0 ;
		if(trList != null )
		for(TransferListItemPO theItem:trList){
			if(theItem != null)
			total += theItem.getTransferMoney() ;
		}
		return total ;
	}
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public boolean isApprovedByManager() {
		return isApprovedByManager;
	}
	public void setApprovedByManager(boolean isApprovedByManager) {
		this.isApprovedByManager = isApprovedByManager;
	}
	public boolean isApprovedByFinancer() {
		return isApprovedByFinancer;
	}
	public void setApprovedByFinancer(boolean isApprovedByFinancer) {
		this.isApprovedByFinancer = isApprovedByFinancer;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	

}