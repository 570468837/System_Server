package DataService.GoodsDataService;

import java.util.ArrayList;

import PO.GoodsClassPO;
import PO.GoodsPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public interface GoodsDataService {

	public GoodsPO getGoodsByID(long id);
	public GoodsPO getGoodsByInfo(String name, String model);
	public ArrayList<GoodsPO> getGoodsPOList();
	public GoodsClassPO getGoodsClassByID(long id);
	public GoodsClassPO getGoodsClassByInfo(String name);
	public ArrayList<GoodsClassPO> getGoodsClassPOList();
	
	public ResultMessage addGoods(GoodsPO goodsPO);
	public ResultMessage delGoods(long id);
	public ResultMessage updGoods(GoodsPO goodsPO);
	public ArrayList<GoodsPO> searchGoods(String info);
	
	public ResultMessage addGoodsClass(GoodsClassPO goodsClassPO);
	public ResultMessage delGoodsClass(long id);
	public ResultMessage updGoodsClass(GoodsClassPO goodsClassPO);
	
	

}
