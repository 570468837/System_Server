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
	public ArrayList<GoodsPO> getGoodsVOList();
	public GoodsClassPO getGoodsClassByID(long id);
	public GoodsClassPO getGoodsClassByInfo(String name);
	public ArrayList<GoodsClassPO> getGoodsClassVOList();
	
	public ResultMessage addGoods(GoodsPO goodsVO);
	public ResultMessage delGoods(long id);
	public ResultMessage updGoods(GoodsPO goodsVO);
	public ArrayList<GoodsPO> searchGoods(String info);
	
	public ResultMessage addGoodsClass(GoodsClassPO goodsClassVO);
	public ResultMessage delGoodsClass(long id);
	public ResultMessage updGoodsClass(GoodsClassPO goodsClassVO);
	
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> receipt);
	public void salesChangeGoods(ArrayList<SalesReceiptPO> receipt);
	
	

}
