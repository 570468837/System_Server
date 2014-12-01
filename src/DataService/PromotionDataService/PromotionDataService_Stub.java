package DataService.PromotionDataService;

import java.util.ArrayList;

import PO.PromotionPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

public class PromotionDataService_Stub implements PromotionDataService{

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

	@Override
	public ArrayList<Object> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionPO> ifPackage(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionPO> ifGift(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionPO> ifVoucher(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
