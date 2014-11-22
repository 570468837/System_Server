package DataService.PromotionDataService;

import PO.PromotionPO;
import ResultMessage.ResultMessage;

public class PromotionController implements PromotionDataService {
	@Override
	public ResultMessage addPackage(PromotionPO po) {
		// TODO Auto-generated method stub
		if(po.getLeastPrice()==100)
			return ResultMessage.add_success;
		else 
			return ResultMessage.add_failure;
	}

	@Override
	public ResultMessage addGift(PromotionPO po) {
		// TODO Auto-generated method stub
		if(po.getLeastPrice()==200)
			return ResultMessage.add_success;
		else 
			return ResultMessage.add_failure;
	}

	@Override
	public ResultMessage addVoucher(PromotionPO po) {
		// TODO Auto-generated method stub
		if(po.getLeastPrice()==300)
			return ResultMessage.add_success;
		else 
			return ResultMessage.add_failure;
	}

	@Override
	public ResultMessage delete(PromotionPO po) {
		// TODO Auto-generated method stub
		if(po.getLeastPrice()==400)
			return ResultMessage.delete_success;
		else 
			return ResultMessage.delete_failure;
	}

}
