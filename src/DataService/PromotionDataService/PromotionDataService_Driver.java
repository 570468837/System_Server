package DataService.PromotionDataService;

import java.util.ArrayList;

import PO.PromotionPO;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class PromotionDataService_Driver {
	public void drive(PromotionController promotiondataservice){
		PromotionPO po = new PromotionPO(null, "001", null, 0, 0, null, 0, null, null, null);
		PromotionPO po1 = new PromotionPO(null, "001", null, 1, 0, null, 0, null, null, null);
		System.out.println(new PromotionController().addGift(po));
		System.out.println(new  PromotionController().delete(po));
		System.out.println(new PromotionController().addGift(po1));
		
		System.out.println(((PromotionPO) new PromotionController().show().get(0)).getPromotionId());
		
	}
	
	public static void main(String[] args){
		PromotionController promotionController=new PromotionController();
		new PromotionDataService_Driver().drive(promotionController);
	}
}
