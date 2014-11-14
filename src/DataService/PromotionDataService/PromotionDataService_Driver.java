package DataService.PromotionDataService;

import PO.PromotionPO;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class PromotionDataService_Driver {
	public void drive(PromotionDataService promotiondataservice){
		PromotionPO po = new PromotionPO(null, null, 0, 0, null, 0, null, null);
		ResultMessage result;
		
		result=promotiondataservice.addPackage(po);
		if(result==ResultMessage.add_success)
			System.out.println("add success!");
		
		result=promotiondataservice.addGift(po);
		if(result==ResultMessage.add_success)
			System.out.println("add success!");
		
		result=promotiondataservice.addVoucher(po);
		if(result==ResultMessage.add_success)
			System.out.println("add success!");
		
		result=promotiondataservice.delete(po);
		if(result==ResultMessage.delete_success)
			System.out.println("delete success!");
	}
	
	public static void main(String[] args){
		PromotionDataService promotionController=new PromotionController();
		new PromotionDataService_Driver().drive(promotionController);
	}
}
