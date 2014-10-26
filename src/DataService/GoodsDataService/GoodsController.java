package DataService.GoodsDataService;

import java.util.ArrayList;

import PO.GoodsClassPO;
import PO.GoodsPO;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public class GoodsController implements GoodsDataService{

	@Override
	public ResultMessage insertGoods(GoodsPO goodsPO) {
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage deleteGoods(GoodsPO goodsPO) {
		return ResultMessage.delete_success;
	}

	@Override
	public ResultMessage updateGoods(GoodsPO goodsPO) {
		return ResultMessage.update_success;
	}

	@Override
	public ArrayList<GoodsPO> searchGoods(String key) {
		
		return new ArrayList<GoodsPO>();
	}

	@Override
	public ResultMessage insertGoodsClass(GoodsClassPO goodsClassPO) {
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage deleteGoodsClass(GoodsClassPO goodsClassPO) {
		return ResultMessage.delete_success;
	}

	@Override
	public ResultMessage updateGoodsClass(GoodsClassPO goodsClassPO) {
		return ResultMessage.update_success;
	}
	
	
}
