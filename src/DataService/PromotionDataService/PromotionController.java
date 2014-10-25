package DataService.PromotionDataService;

import PO.PromotionPO;
import ResultMessage.ResultMessage;

public class PromotionController implements PromotionDataService {
	@Override
	public ResultMessage addPackage(PromotionPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage addGift(PromotionPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage addVoucher(PromotionPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delete(PromotionPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.delete_success;
	}

}
