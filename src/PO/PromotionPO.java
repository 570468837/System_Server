package PO;

public class PromotionPO {
	 String promotionType;
     String goodsId;
     double leastPrice;
     double offPrice;
     String presentGoodsId;
     int presentNum;
     String startTime;
   	 String endTime;
 	
     public String getGoodsId() {
		return goodsId;
	}
	public double getLeastPrice() {
		return leastPrice;
	}
	public double getOffPrice() {
		return offPrice;
	}
	public String getPresentGoodsId() {
		return presentGoodsId;
	}
	public int getPresentNum() {
		return presentNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}

}
