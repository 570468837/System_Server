package DataService.PromotionDataService;

import PO.PromotionPO;
import ResultMessage.ResultMessage;

public class PromotionDataService_Stub implements PromotionDataService{

	@Override
	public ResultMessage addPackage(PromotionPO po) {
		// TODO Auto-generated method stub
		System.out.println("add success!");
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage addGift(PromotionPO po) {
		// TODO Auto-generated method stub
		System.out.println("add success!");
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage addVoucher(PromotionPO po) {
		// TODO Auto-generated method stub
		System.out.println("add success!");
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delete(PromotionPO po) {
		// TODO Auto-generated method stub
		System.out.println("delete success!");
		return ResultMessage.delete_success;
	}
	

}
