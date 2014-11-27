package PO;
/**
 * 
 * @author hutao
 *
 */
public class SendCommodityPO {
	public long goodsVOId;
	public String customerVOName;
	public int num;
	
	public SendCommodityPO() {}
	public SendCommodityPO(long goodsVOId, String customerVOName, int num) {
		this.goodsVOId = goodsVOId;
		this.customerVOName = customerVOName;
		this.num = num;
	}

}
