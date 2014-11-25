package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesReceiptPO implements Serializable{
	private String serialNumber;
	// 销售商
	private String retailer;
	// 业务员
	private String salesman;
	private UserPO userPO;
	private String commodityNum;
	private double priveBefore;
	private double discout;
	private double finalprice;
	private String comment;

	private ArrayList<SalesListItemPO> salesList = new ArrayList<SalesListItemPO>();

	// 添加商品
	public void addSalesListItem(SalesListItemPO salesListItem) {
		this.salesList.add(salesListItem);

	}

	public ArrayList<SalesListItemPO> getSalesList() {
		return salesList;
	}

	public void setSalesList(ArrayList<SalesListItemPO> salesList) {
		this.salesList = salesList;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public UserPO getUserPO() {
		return userPO;
	}

	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}

	public String getCommodityPO() {
		return commodityNum;
	}

	public void setCommodityPO(String commodityNum) {
		this.commodityNum = commodityNum;
	}

	public double getPriveBefore() {
		// 遍历所有商品以获得总价
		for (int i = 0; i < salesList.size(); i++) {
			this.priveBefore += salesList.get(i).getTotalPrice();
		}
		return priveBefore;
	}

	public void setPriveBefore(long priveBefore) {
		this.priveBefore = priveBefore;
	}

	public double getDiscout() {
		return discout;
	}

	public void setDiscout(long discout) {
		this.discout = discout;
	}

	public double getFinalprice() {
		// 减去折扣
		this.finalprice = this.priveBefore - this.discout;
		return finalprice;
	}

	public void setFinalprice(long finalprice) {
		this.finalprice = finalprice;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
