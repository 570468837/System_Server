package DataService.PromotionDataService;

import java.util.ArrayList;

import PO.PromotionPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

public interface PromotionDataService {
		public ResultMessage addPackage(PromotionPO po);
		public ResultMessage addGift(PromotionPO po);
		public ResultMessage addVoucher(PromotionPO po);
		public ResultMessage delete(PromotionPO po); 
		public ArrayList<Object> show();
		public ArrayList<Object> ifPackage(SalesReceiptPO receipt);
		public ArrayList<Object> ifGift(SalesReceiptPO receipt);
		public ArrayList<Object> ifVoucher(SalesReceiptPO receipt);
		
	
}
