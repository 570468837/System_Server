package DataService.PromotionDataService;

import java.util.ArrayList;

import Config.Level;
import Config.PromotionSort;
import PO.GoodsPO;
import PO.PromotionPO;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class PromotionDataService_Driver {
	public void drive(PromotionController promotiondataservice){
		ArrayList<GoodsPO> g=new ArrayList<GoodsPO>();
		//g.add(new GoodsPO("123","name","model",100,1000,0, 0, "暂无"));
		PromotionPO po = new PromotionPO(PromotionSort.Voucher, "001", null, 100, 0, null, 10, "20140123", "20141112", Level.forthClass);
		PromotionPO po1 = new PromotionPO(PromotionSort.Gifts, "002", null, 100, 0, new ArrayList<GoodsPO>(), 0,"20141023", "20141123", Level.fiveClassVIP);
		PromotionPO po2 = new PromotionPO(PromotionSort.Package, "003", new ArrayList<GoodsPO>() , 0, 100, null, 0, "20141111", "20141201", Level.firstClass);
		PromotionPO po3 = new PromotionPO(PromotionSort.Package, "004", g , 0, 100, null, 0, "20141111", "20141201", Level.firstClass);
		
		System.out.println(new PromotionController().addVoucher(po));
		System.out.println((new PromotionController().addGift(po1)));
		System.out.println((new PromotionController().addPackage(po2)));
		//System.out.println((new PromotionController().delete(po3)));
		System.out.println((new PromotionController().addPackage(po3)));
		System.out.println(new PromotionController().show().size());
	}
	
	public static void main(String[] args){
		PromotionController promotionController=new PromotionController();
		new PromotionDataService_Driver().drive(promotionController);
	}
}
