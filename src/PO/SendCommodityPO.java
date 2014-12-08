package PO;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author hutao
 *
 */
public class SendCommodityPO {
	public long goodsPOId;
	public String customerPOName;
	public int num;
	public int type = UNCHECKED;
	public Date date;
	
	public SendCommodityPO() {}
	/**
	 * 赠送单的创建方法，并自动生成创建时间
	 */
	public SendCommodityPO(long goodsPOId, String customerPOName, int num) {
		this.goodsPOId = goodsPOId;
		this.customerPOName = customerPOName;
		this.num = num;
		date = new Date();//自动生成系统时间
	}
	public SendCommodityPO(SendCommodityPO po) {
		this.goodsPOId = po.goodsPOId;
		this.customerPOName = po.customerPOName;
		this.num = po.num;
	}
	
	public static final int UNCHECKED = 0;
	public static final int PASS = 1;
	public static final int CANCEL= 2;
	
	

}
