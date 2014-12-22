package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesReceiptPO implements Serializable {
	private CustomerPO customerPO;
	
	public CustomerPO getCustomerPO() {
		return customerPO;
	}

	public void setCustomerPO(CustomerPO customerPO) {
		this.customerPO = customerPO;
	}
	
	private String serialNumber;
	// 销售商
	private String retailer;
	// 业务员
	private String salesman;
	private UserPO userPO;
	private String commodityNum;
	private double priceBefore;
	private double discout;
	private double finalprice;
	private String comment;
	private String time;
	private PromotionPO promotionPO;
	private double vocher;
	public double getVocher() {
		return vocher;
	}

	public void setVocher(double vocher) {
		this.vocher = vocher;
	}
	
	public PromotionPO getPromotionPO() {
		return promotionPO;
	}

	public void setPromotionPO(PromotionPO promotionPO) {
		this.promotionPO = promotionPO;
	}
	private boolean isApprovedByManager=false;
	private boolean isApprovedByCommodity=false;
	
	public boolean isApprovedByManager() {
		return isApprovedByManager;
	}


	public void setApprovedByManager(boolean isApprovedByManager) {
		this.isApprovedByManager = isApprovedByManager;
	}


	public boolean isApprovedByCommodity() {
		return isApprovedByCommodity;
	}


	public void setApprovedByCommodity(boolean isApprovedByCommodity) {
		this.isApprovedByCommodity = isApprovedByCommodity;
	}


	
	private ArrayList<SalesListItemPO> salesList;
	
	public SalesReceiptPO(){}
	public SalesReceiptPO(String serialNumber, String retailer,
			String salesman, ArrayList<SalesListItemPO> salesList,
			UserPO userPO, String commodityNum, double priceBefore,
			double discount, double finalPrice, String time, String comments) {
		this.serialNumber=serialNumber;
		this.retailer=retailer;
		this.salesman=salesman;
		this.userPO=userPO;
		this.commodityNum=commodityNum;
		this.priceBefore=priceBefore;
		this.discout=discount;
		this.finalprice=finalPrice;
		this.comment=comments;
		this.time=time;
		this.salesList=salesList;

	}
	
	public SalesReceiptPO(SalesReceiptPO po){
		this.setComment(po.getComment());
		this.setApprovedByCommodity(po.isApprovedByCommodity);
		this.setApprovedByManager(po.isApprovedByManager);
		this.setCommodityNum(po.commodityNum);
		this.setCustomerPO(po.getCustomerPO());
		this.setDiscout(po.getDiscout());
		this.setFinalprice(po.getFinalprice());
		this.setPriceBefore(po.getPriceBefore());
		this.setPromotionPO(po.getPromotionPO());
		this.setRetailer(po.getRetailer());
		this.setSalesList(po.getSalesList());
		this.setVocher(po.getVocher());
		this.setUserPO(po.getUserPO());
		this.setTime(po.getTime());
		this.setSerialNumber(po.getSerialNumber());
	}
	


	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}

	public void setCommodityNum(String commodityNum) {
		this.commodityNum = commodityNum;
	}

	public void setDiscout(double discout) {
		this.discout = discout;
	}

	public void setFinalprice(double finalprice) {
		this.finalprice = finalprice;
	}

	

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

	public void setUserVO(UserPO userPO) {
		this.userPO = userPO;
	}

	public String getCommodityNum() {
		return commodityNum;
	}

	public void setCommodityVO(String commodityNum) {
		this.commodityNum = commodityNum;
	}

	public double getPriceBefore() {
		double result=0;
		// 遍历所有商品以获得总价
		for (int i = 0; i < salesList.size(); i++) {
			result += salesList.get(i).getTotalPrice();
		}
		return result;
	}

	public void setPriceBefore(double priceBefore) {
		this.priceBefore = priceBefore;
	}

	public double getDiscout() {
		return discout;
	}

	

	public double getFinalprice() {
//		// 减去折扣
		this.finalprice = this.priceBefore - this.discout;
		return finalprice;
	}

	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
