package DataService.GoodsDataService;

import java.util.*;

import PO.*;
import ResultMessage.ResultMessage;


/**
 * 
 * @author hutao
 *
 */
public class GoodsDataService_Driver {
    public void drive(GoodsDataService goodsController) {
		ResultMessage resultMessage = goodsController.insertGoods(new GoodsPO());
    	if(resultMessage == ResultMessage.add_success) {
    		System.out.println("Goods add success");
    	}
    	else {
    		System.out.println("Goods add failure");
    	}
    	
    	resultMessage = goodsController.deleteGoods(new GoodsPO());
    	if(resultMessage == ResultMessage.delete_success) {
    		System.out.println("Goods del success");
    	}
    	else {
    		System.out.println("Goods del failure");
    	}
    	
    	resultMessage = goodsController.updateGoods(new GoodsPO());
    	if(resultMessage == ResultMessage.update_success) {
    		System.out.println("Goods upd success");
    	}
    	else {
    		System.out.println("Goods upd failure");
    	}
    	
    	ArrayList<GoodsPO> GoodsPOList = goodsController.searchGoods(new String());
    	if(GoodsPOList.size() != 0) {
    		System.out.println("Good shave searched");
    	}
    	else {
    		System.out.println("no match goods");
    	}
    	
    	resultMessage = goodsController.insertGoodsClass(new GoodsClassPO());
    	if(resultMessage == ResultMessage.add_success) {
    		System.out.println("GoodsClass add success");
    	}
    	else {
    		System.out.println("GoodsClass dd failure");
    	}
    	
    	resultMessage = goodsController.deleteGoodsClass(new GoodsClassPO());
    	if(resultMessage == ResultMessage.delete_success) {
    		System.out.println("GoodsClass delete success");
    	}
    	else {
    		System.out.println("GoodsClass delete failure");
    	}
    	
    	resultMessage = goodsController.updateGoodsClass(new GoodsClassPO());
    	if(resultMessage == ResultMessage.add_success) {
    		System.out.println("GoodsClass upd success");
    	}
    	else {
    		System.out.println("GoodsClass upd failure");
    	}
    	
	}
	
	
	public static void main(String[] args) {
		GoodsDataService_Driver g = new GoodsDataService_Driver();
		g.drive(new GoodsController());
	}
	

}
