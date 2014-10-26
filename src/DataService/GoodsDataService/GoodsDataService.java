package DataService.GoodsDataService;

import java.util.*;

import PO.*;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public interface GoodsDataService {
	public ResultMessage insertGoods(GoodsPO goodsPO);
	public ResultMessage deleteGoods(GoodsPO goodsPO);
	public ResultMessage updateGoods(GoodsPO goodsPO);
	public ArrayList<GoodsPO> searchGoods(String key);
	
	public ResultMessage insertGoodsClass(GoodsClassPO goodsClassPO);
	public ResultMessage deleteGoodsClass(GoodsClassPO goodsClassPO);
	public ResultMessage updateGoodsClass(GoodsClassPO goodsClassPO);
	
	

}
